package com.github.ferum_bot.twentyone.ui;

import com.github.ferum_bot.twentyone.models.players.PlayerInfo;

import java.util.Collection;

/**
 * Handles output commands.
 * @author matvejpopov
 * @version 1.0.0
 * @see PlayerInfo
 */
public interface OutputHandler {

    /**
     * Called when game started.
     */
    void OnGameStart();

    /**
     * Called after game was finished. When 10 seconds passed.
     * @param players information about each player.
     * @param winner information about winner.
     * @see PlayerInfo
     */
    void onGameFinished(Collection<PlayerInfo> players, PlayerInfo winner);
}
