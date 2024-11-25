package com.golf.tournament.service;

import org.springframework.stereotype.Service;

import com.golf.tournament.exception.PlayerNotFoundException;
import com.golf.tournament.model.Player;
import com.golf.tournament.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player addPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        return playerRepository.save(player);
    }

    public Player getPlayer(String name) {
        Player player = playerRepository.findByName(name);
        if (player == null) {
            throw new PlayerNotFoundException("Player with name " + name + " not found");
        }
        return player;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}

