package com.ferumbot.jigsaw.client.exception;

public class GameNotStartedException extends RuntimeException {

    public GameNotStartedException() {
        super("Game not started!");
    }

    public GameNotStartedException(String message) {
        super(message);
    }
}
