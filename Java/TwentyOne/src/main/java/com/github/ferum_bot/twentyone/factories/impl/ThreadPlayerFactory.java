package com.github.ferum_bot.twentyone.factories.impl;

import com.github.ferum_bot.twentyone.factories.PlayerFactory;
import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.models.game.GameTable;
import com.github.ferum_bot.twentyone.models.players.Player;
import com.github.ferum_bot.twentyone.models.players.thread.CheaterThreadPlayer;
import com.github.ferum_bot.twentyone.models.players.thread.CommonThreadPlayer;
import com.github.ferum_bot.twentyone.models.players.thread.ThreadPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ThreadPlayerFactory implements PlayerFactory {

    private int counter = 0;

    @Override
    public Collection<? extends Player> createPlayersWith(GameParameters parameters, GameTable table) {
        var commonPlayers = new ArrayList<ThreadPlayer>();
        var cheaterPlayers = new ArrayList<ThreadPlayer>();
        var commonCount = parameters.numberOfCommonPlayers();
        var cheaterCount = parameters.numberOfCheaters();

        for (int i = 0; i < commonCount; i++) {
            commonPlayers.add((ThreadPlayer)createCommonPlayer(table));
        }
        for (int i = 0; i < cheaterCount; i++) {
            cheaterPlayers.add(createCheaterPlayer(table, commonPlayers));
        }

        commonPlayers.addAll(cheaterPlayers);
        return commonPlayers;
    }

    @Override
    public Player createCommonPlayer(GameTable table) {
        return new CommonThreadPlayer(table, counter++);
    }

    @Override
    public Player createCheaterPlayer(GameTable table) {
        return new CheaterThreadPlayer(table, counter++, Collections.emptyList());
    }

    private ThreadPlayer createCheaterPlayer(GameTable table, Collection<ThreadPlayer> commonPlayers) {
        return new CheaterThreadPlayer(table, counter++, commonPlayers);
    }
}
