package com.github.ferum_bot.twentyone.ui;

import com.github.ferum_bot.twentyone.models.players.PlayerInfo;
import com.github.ferum_bot.twentyone.controllers.GameController;
import java.util.Collection;

/**
 * Handles output commands.
 * @author matvejpopov
 * @version 1.0.0
 * @see PlayerInfo
 * @see GameController
 */
public interface OutputHandler {

    /**
     * Called when game started.
     */
    void onGameStart();

    /**
     * Called to input game parameters.
     * @see GameController
     */
    void inputGameParameters();

    /**
     * Called if input game parameters is invalid.
     * @see GameController
     */
    void invalidGameParameters();

    /**
     * Called if input game parameters is valid.
     * Also provides the game starts message.
     * @see GameController
     */
    void parametersIsValid();

    /**
     * Called after game was finished. When 10 seconds passed.
     * @param players information about each player.
     * @param winner information about winner.
     * @see PlayerInfo
     */
    void onGameFinished(Collection<PlayerInfo> players, PlayerInfo winner);
}
