package com.github.ferum_bot.twentyone.ui;

import com.github.ferum_bot.twentyone.models.game.GameParameters;

/**
 * Handles input commands.
 * @author matvejpopov
 * @version 1.0.0
 * @see GameParameters
 */
public interface InputHandler {

    /**
     * Method to get game parameters.
     * @return game parameters.
     * @see GameParameters
     */
    GameParameters getParameters();
}
