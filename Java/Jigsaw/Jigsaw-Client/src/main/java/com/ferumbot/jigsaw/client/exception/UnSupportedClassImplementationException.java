package com.ferumbot.jigsaw.client.exception;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;

/**
 * Indicates that an unsupported class implementation was passed to the game client.
 * @author matvejpopov
 * @see JigsawGameClient
 * @version 1.0.0
 */
public class UnSupportedClassImplementationException extends RuntimeException {

    public UnSupportedClassImplementationException(String message) {
        super(message);
    }
}
