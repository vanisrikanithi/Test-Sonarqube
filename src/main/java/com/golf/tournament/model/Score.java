package com.golf.tournament.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Score {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int holeNumber;
    private int strokes;
    private int relativeToPar;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonManagedReference // To handle bidirectional relationship
    private Player player;

	public Score(Long id, int holeNumber, int strokes, int relativeToPar, Player player) {
		super();
		this.id = id;
		this.holeNumber = holeNumber;
		this.strokes = strokes;
		this.relativeToPar = relativeToPar;
		this.player = player;
	}

	public Score() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHoleNumber() {
		return holeNumber;
	}

	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}

	public int getStrokes() {
		return strokes;
	}

	public void setStrokes(int strokes) {
		this.strokes = strokes;
	}

	public int getRelativeToPar() {
		return relativeToPar;
	}

	public void setRelativeToPar(int relativeToPar) {
		this.relativeToPar = relativeToPar;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public String getRelativeToParDisplay() {
        if (relativeToPar == 0) {
            return "E";  // Even with par
        } else if (relativeToPar < 0) {
            return Integer.toString(relativeToPar);  // Under par
        } else {
            return "+" + relativeToPar;  // Over par
        }
    }
    
}

