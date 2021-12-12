package com.github.ferum_bot.twentyone.ui.impl;

import com.github.ferum_bot.twentyone.exceptions.InvalidInputException;
import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.ui.InputHandler;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public GameParameters getParameters() {
        var input = scanner.nextLine();
        GameParameters result;

        try {
            result = parseAndGetParameters(input);
        } catch (Exception ex) {
            throw new InvalidInputException(ex.getMessage());
        }

        return result;
    }

    private GameParameters parseAndGetParameters(String input) {
        var inputParams = input.split(" ");
        var numberOfCommonPlayers = Integer.parseInt(inputParams[0]);
        var numberOfCheaters = Integer.parseInt(inputParams[1]);

        return new GameParameters(numberOfCommonPlayers, numberOfCheaters);
    }
}
