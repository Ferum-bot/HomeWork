package com.github.ferum_bot.twentyone;

import com.github.ferum_bot.twentyone.controllers.GameController;
import com.github.ferum_bot.twentyone.ui.impl.ConsoleInputHandler;
import com.github.ferum_bot.twentyone.ui.impl.ConsoleOutputHandler;

public class TwentyOneMain {

    public static void main(String[] args) {
        var inputHandler = new ConsoleInputHandler();
        var outputHandler = new ConsoleOutputHandler();
        var controller = new GameController(inputHandler, outputHandler);

        controller.start();
    }
}
