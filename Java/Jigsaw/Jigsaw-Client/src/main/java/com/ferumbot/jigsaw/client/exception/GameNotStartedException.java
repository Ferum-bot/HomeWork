package com.ferumbot.jigsaw.client.exception;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;

/**
 * Indicates that game wasn't started.
 * @author matvejpopov
 * @see JigsawGameClient
 * @version 1.0.0
 */
public class GameNotStartedException extends RuntimeException {

    public GameNotStartedException() {
        super("Game not started!");
    }

    public GameNotStartedException(String message) {
        super(message);
    }
}
