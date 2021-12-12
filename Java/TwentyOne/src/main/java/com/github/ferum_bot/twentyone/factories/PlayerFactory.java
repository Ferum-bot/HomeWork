package com.github.ferum_bot.twentyone.factories;

import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.models.game.GameTable;
import com.github.ferum_bot.twentyone.models.players.Player;

import java.util.Collection;

/**
 * Factory that creates players for game.
 * @author matvejpopov
 * @version 1.0.0
 * @see Player
 * @see GameParameters
 * @see GameTable
 */
public interface PlayerFactory {

    /**
     * Creates players with given game params and table.
     * @param parameters game parameters.
     * @param table game table.
     * @return created game players.
     * @see Player
     * @see GameTable
     * @see GameParameters
     */
    Collection<? extends Player> createPlayersWith(GameParameters parameters, GameTable table);

    /**
     * Creates common player with given table.
     * @param table game table.
     * @return created common player.
     * @see Player
     * @see GameTable
     */
    Player createCommonPlayer(GameTable table);

    /**
     * Creates cheater player with given table.
     * @param table game table.
     * @return created cheater player.
     * @see Player
     * @see GameTable
     */
    Player createCheaterPlayer(GameTable table);
}
