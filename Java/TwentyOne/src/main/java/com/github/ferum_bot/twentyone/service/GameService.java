package com.github.ferum_bot.twentyone.service;

import com.github.ferum_bot.twentyone.factories.PlayerFactory;
import com.github.ferum_bot.twentyone.factories.impl.ThreadPlayerFactory;
import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.models.game.GameTable;
import com.github.ferum_bot.twentyone.models.players.Player;
import com.github.ferum_bot.twentyone.models.players.PlayerInfo;

import java.util.Collection;

/**
 * Main game service. Starts the game process.
 * @author matvejpopov
 * @version 1.0.0
 * @see GameTable
 * @see Player
 * @see GameParameters
 */
public class GameService {

    private static final int GAME_DURATION_MILLIS = 10_000;

    private static final PlayerFactory playerFactory = new ThreadPlayerFactory();

    private final Collection<? extends Player> players;

    public GameService(GameParameters gameParameters) {
        var gameTable = new GameTable();
        players = playerFactory.createPlayersWith(gameParameters, gameTable);
    }

    public PlayerInfo getWinner() {
        return players.stream().max(this::comparePlayers).get().getInformation();
    }

    public Collection<PlayerInfo> getPlayersInfo() {
        return players.stream().map(Player::getInformation).toList();
    }

    public void startGame() {
        startPlayers();

        try {
            Thread.sleep(GAME_DURATION_MILLIS);
        } catch (InterruptedException ignored) { }

        stopPlayers();
    }

    private void startPlayers() {
        players.forEach(Player::startPlaying);
    }

    private void stopPlayers() {
        players.forEach(Player::stopPlaying);
    }

    private int comparePlayers(Player first, Player second) {
        var firstScore = first.getInformation().score();
        var secondScore = second.getInformation().score();

        return Integer.compare(firstScore, secondScore);
    }
}
