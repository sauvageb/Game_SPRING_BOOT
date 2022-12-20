package com.training.games.service.exception;

public class GameNotFoundException extends Exception {

    public GameNotFoundException(Long id) {
        super("Game with id " + id + " is not found");
    }
}
