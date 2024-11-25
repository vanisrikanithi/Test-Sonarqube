package com.golf.tournament.service;

import org.springframework.stereotype.Service;

import com.golf.tournament.dto.LeaderboardResponseDTO;
import com.golf.tournament.exception.InvalidHoleException;
import com.golf.tournament.exception.PlayerNotFoundException;
import com.golf.tournament.model.Player;
import com.golf.tournament.model.Score;
import com.golf.tournament.repository.ScoreRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final PlayerService playerService;

    // Fixed par layout for 18 holes
    private final int[] parLayout = {4, 5, 3, 4, 5, 4, 4, 3, 4, 4, 4, 4, 4, 5, 4, 3, 5, 3};

    public ScoreService(ScoreRepository scoreRepository, PlayerService playerService) {
        this.scoreRepository = scoreRepository;
        this.playerService = playerService;
    }

    public Score submitScore(String playerName, int holeNumber, int strokes) {
        if (holeNumber < 1 || holeNumber > 18) {
            throw new InvalidHoleException("Invalid hole number: " + holeNumber);
        }

        Player player = playerService.getPlayer(playerName);
        
        if (player == null) {
            throw new PlayerNotFoundException("Player with name " + playerName + " not found.");
        }
        int relativeToPar = strokes - parLayout[holeNumber - 1];

        Score score = new Score();
        score.setHoleNumber(holeNumber);
        score.setStrokes(strokes);
        score.setRelativeToPar(relativeToPar);
        score.setPlayer(player);

        return scoreRepository.save(score);
    }
    
    /**
     * Retrieves all the scores for a specific player.
     */
    public List<Score> getScoresForPlayer(String playerName) {
        Player player = playerService.getPlayer(playerName);
        if (player == null) {
            throw new PlayerNotFoundException("Player with name " + playerName + " not found.");
        }
        return scoreRepository.findByPlayerId(player.getId());
    }

    public List<Score> getScoresForPlayer(Long playerId) {
        return scoreRepository.findByPlayerId(playerId);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }
    
    public List<LeaderboardResponseDTO> getLeaderboard() {
        List<Score> allScores = scoreRepository.findAll();

        // Group scores by player and sum the relative scores to par
        Map<Player, Integer> playerScores = allScores.stream()
                .collect(Collectors.groupingBy(Score::getPlayer, Collectors.summingInt(Score::getRelativeToPar)));

        // Convert the map entries into a sorted list of LeaderboardEntryDTO
        return playerScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(entry -> new LeaderboardResponseDTO(
                        entry.getKey().getName(),
                        entry.getValue(),
                        entry.getKey().getScores().size()
                ))
                .collect(Collectors.toList());
    }
}
