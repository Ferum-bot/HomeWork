package com.github.ferum_bot.twentyone.exceptions;

import com.github.ferum_bot.twentyone.ui.InputHandler;

/**
 * Shows invalid input.
 * @author matvejpopov
 * @version 1.0.0
 * @see InputHandler
 */
public class InvalidInputException extends IllegalArgumentException {

    public InvalidInputException(String message) {
        super(message);
    }
}
