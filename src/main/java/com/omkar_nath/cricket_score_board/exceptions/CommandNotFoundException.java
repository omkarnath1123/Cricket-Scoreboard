package com.omkar_nath.cricket_score_board.exceptions;

public class CommandNotFoundException extends RuntimeException {

    private final String message;

    public CommandNotFoundException(String name) {
        this.message = name;
    }

    @Override
    public String getMessage() {
        return "Command " + message + " not found";
    }
}
