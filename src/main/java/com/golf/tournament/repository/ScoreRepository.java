package com.golf.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.golf.tournament.model.Score;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByPlayerId(Long playerId);
}

