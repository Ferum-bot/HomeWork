package com.github.ferum_bot.twentyone.controllers;

import com.github.ferum_bot.twentyone.core.guard.Guard;
import com.github.ferum_bot.twentyone.core.guard.GuardResult;
import com.github.ferum_bot.twentyone.core.guard.impl.GameParametersGuard;
import com.github.ferum_bot.twentyone.exceptions.InvalidInputException;
import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.service.GameService;
import com.github.ferum_bot.twentyone.ui.InputHandler;
import com.github.ferum_bot.twentyone.ui.OutputHandler;

public class GameController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private final Guard<GameParameters> gameParametersGuard = new GameParametersGuard();

    public GameController(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void start() {
        var parameters = getParameters();
        var gameService = new GameService(parameters);

        outputHandler.OnGameStart();
        gameService.startGame();

        var winner = gameService.getWinner();
        var playersInfo = gameService.getPlayersInfo();

        outputHandler.onGameFinished(playersInfo, winner);
    }

    private GameParameters getParameters() {
        GameParameters parameters = null;

        while (parameters == null) {
            parameters = tryToGetGameParameters();
        }

        return parameters;
    }

    private GameParameters tryToGetGameParameters() {
        GameParameters parameters;
        try {
            parameters = inputHandler.getParameters();
            var guardResult = gameParametersGuard.obtainValue(parameters);
            if (guardResult != GuardResult.SUCCESS) {
                return null;
            }
        } catch (InvalidInputException exception) {
            return null;
        }
        return parameters;
    }
}
