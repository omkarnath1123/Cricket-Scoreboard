package com.omkar_nath.cricket_score_board.excptions;

public class InvalidGameState extends RuntimeException {

    private final String message;

    public InvalidGameState(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Invalid game state : " + message;
    }
}
