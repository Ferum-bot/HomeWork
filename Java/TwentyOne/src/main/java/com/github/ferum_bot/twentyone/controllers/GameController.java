package com.github.ferum_bot.twentyone.controllers;

import com.github.ferum_bot.twentyone.core.guard.Guard;
import com.github.ferum_bot.twentyone.core.guard.GuardResult;
import com.github.ferum_bot.twentyone.core.guard.impl.GameParametersGuard;
import com.github.ferum_bot.twentyone.exceptions.InvalidInputException;
import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.service.GameService;
import com.github.ferum_bot.twentyone.ui.InputHandler;
import com.github.ferum_bot.twentyone.ui.OutputHandler;

/**
 * Game entrypoint. Configures game and handles errors.
 * @author matvejpopov
 * @version 1.0.0
 * @see GameService
 * @see InputHandler
 * @see OutputHandler
 */
public class GameController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private final Guard<GameParameters> gameParametersGuard = new GameParametersGuard();

    public GameController(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void start() {
        outputHandler.onGameStart();

        var parameters = getParameters();
        var gameService = new GameService(parameters);

        gameService.startGame();

        var winner = gameService.getWinner();
        var playersInfo = gameService.getPlayersInfo();

        outputHandler.onGameFinished(playersInfo, winner);
    }

    private GameParameters getParameters() {
        outputHandler.inputGameParameters();
        GameParameters parameters = null;

        while (parameters == null) {
            parameters = tryToGetGameParameters();
        }

        outputHandler.parametersIsValid();
        return parameters;
    }

    private GameParameters tryToGetGameParameters() {
        GameParameters parameters;
        try {
            parameters = inputHandler.getParameters();
            var guardResult = gameParametersGuard.obtainValue(parameters);
            if (guardResult != GuardResult.SUCCESS) {
                outputHandler.invalidGameParameters();
                return null;
            }
        } catch (InvalidInputException exception) {
            outputHandler.invalidGameParameters();
            return null;
        }
        return parameters;
    }
}
