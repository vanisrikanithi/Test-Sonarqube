package com.golf.tournament.controller;

import com.golf.tournament.model.Player;
import com.golf.tournament.service.PlayerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    public Player addPlayer(@RequestParam String name) {
        return playerService.addPlayer(name);
    }

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}

