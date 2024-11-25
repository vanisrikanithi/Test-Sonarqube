package com.golf.tournament.exception;

public class InvalidHoleException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidHoleException(String message) {
        super(message);
    }
}
