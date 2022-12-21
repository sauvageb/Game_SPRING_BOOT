package com.training.games.controller.exception;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String username) {
        super(username + " already exist in database");
    }
}
