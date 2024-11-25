package com.golf.tournament.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.golf.tournament.dto.LeaderboardResponseDTO;
import com.golf.tournament.dto.PlayerReq;
import com.golf.tournament.model.Score;
import com.golf.tournament.service.ScoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/submit")
    public Score submitScore(
            @Valid @RequestBody PlayerReq playerReq, BindingResult result) throws IOException {

    	// Check if validation errors exist
        if (result.hasErrors()) {
            // Return the first validation error as a bad request response
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        }
        
        Score score = scoreService.submitScore(playerReq.getName(), playerReq.getHoleNumber(), playerReq.getStroke());

        // Trigger WebSocket alert if the player is under par for the hole
        if (score.getRelativeToPar() < 0) {
            //TODO: Using web socket we need to send a notification to client
        	System.out.println("Player " + playerReq.getName() + " scored under par for hole " + playerReq.getHoleNumber());
        }
        
        // Return the score, including how it compares to par ("E" for even, negative for under par, etc.)
        System.out.println("Player: " + playerReq.getName() + ", Score for hole " + playerReq.getHoleNumber() + ": " + score.getRelativeToParDisplay());

        return score;
    }

    @GetMapping("/leaderboard")
    public List<LeaderboardResponseDTO> getLeaderboard() {
        return scoreService.getLeaderboard();
    }

    @GetMapping("/player/{name}")
    public List<Score> getPlayerScores(@PathVariable String name) {
        return scoreService.getScoresForPlayer(name);
    }
}

