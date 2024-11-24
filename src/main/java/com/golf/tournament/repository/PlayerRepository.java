package com.golf.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.golf.tournament.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByName(String name);
}

