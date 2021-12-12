package com.github.ferum_bot.twentyone.models.players;

import com.github.ferum_bot.twentyone.models.game.GameTable;
import com.github.ferum_bot.twentyone.service.GameService;

/**
 * The player entity. Provides base methods to control behavior.
 * @author matvejpopov
 * @version 1.0.0
 * @see GameTable
 * @see PlayerInfo
 * @see GameService
 */
public interface Player {

    /**
     * Enables current player.
     */
    void startPlaying();

    /**
     * Disables current player.
     */
    void stopPlaying();

    /**
     * Sets new game table to take cards from.
     * @param table thr game table.
     * @see GameTable
     */
    void setGameTable(GameTable table);

    /**
     * Provides base player information.
     * @return base player information.
     * @see PlayerInfo
     */
    PlayerInfo getInformation();
}
