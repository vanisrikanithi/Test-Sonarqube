package com.golf.tournament.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PlayerReq {

	@NotNull(message = "Player name cannot be null")
	private String name;
	
	@NotNull(message = "Stroke cannot be null")
    @Min(value = 1, message = "Stroke must be at least 1")
	private Integer stroke;
	
	@NotNull(message = "Hole number cannot be null")
    @Min(value = 1, message = "Hole number must be at least 1")
    @Max(value = 18, message = "Hole number must be at most 18")
	private Integer holeNumber;
	
	public PlayerReq() {
		super();
	}

	public PlayerReq(String name, Integer stroke, Integer holeNumber) {
		super();
		this.name = name;
		this.stroke = stroke;
		this.holeNumber = holeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStroke() {
		return stroke;
	}

	public void setStroke(Integer stroke) {
		this.stroke = stroke;
	}

	public Integer getHoleNumber() {
		return holeNumber;
	}

	public void setHoleNumber(Integer holeNumber) {
		this.holeNumber = holeNumber;
	}
}
