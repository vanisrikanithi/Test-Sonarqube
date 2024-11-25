package com.golf.tournament.dto;

public class LeaderboardResponseDTO {

    private String playerName;
    private Integer totalScoreRelativeToPar;
    private Integer holesCompleted;

    public LeaderboardResponseDTO(String playerName, Integer totalScoreRelativeToPar, Integer holesCompleted) {
        this.playerName = playerName;
        this.totalScoreRelativeToPar = totalScoreRelativeToPar;
        this.holesCompleted = holesCompleted;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getTotalScoreRelativeToPar() {
        return totalScoreRelativeToPar;
    }

    public void setTotalScoreRelativeToPar(Integer totalScoreRelativeToPar) {
        this.totalScoreRelativeToPar = totalScoreRelativeToPar;
    }

    public Integer getHolesCompleted() {
        return holesCompleted;
    }

    public void setHolesCompleted(Integer holesCompleted) {
        this.holesCompleted = holesCompleted;
    }
}
