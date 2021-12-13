package com.github.ferum_bot.twentyone.core.guard.impl;

import com.github.ferum_bot.twentyone.core.guard.Guard;
import com.github.ferum_bot.twentyone.core.guard.GuardResult;
import com.github.ferum_bot.twentyone.models.game.GameParameters;

public class GameParametersGuard implements Guard<GameParameters> {

    private static final int MAX_PLAYERS_COUNT = 100;
    private static final int MIN_PLAYERS_COUNT = 0;

    @Override
    public GuardResult obtainValue(GameParameters value) {
        var totalPlayers = value.numberOfCommonPlayers() + value.numberOfCheaters();

        if (value.numberOfCheaters() < 0 || value.numberOfCommonPlayers() < 0) {
            return GuardResult.FAILURE;
        }
        if (value.numberOfCommonPlayers() < 2) {
            return GuardResult.FAILURE;
        }
        if (totalPlayers <= MIN_PLAYERS_COUNT || totalPlayers >= MAX_PLAYERS_COUNT) {
            return GuardResult.FAILURE;
        }

        return GuardResult.SUCCESS;
    }
}
