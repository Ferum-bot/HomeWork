package com.github.ferum_bot.twentyone.ui;

import com.github.ferum_bot.twentyone.models.players.PlayerInfo;
import com.github.ferum_bot.twentyone.ui.impl.ConsoleOutputHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OutputHandlerTest {

    private OutputHandler outputHandler;

    @BeforeEach
    public void onStart() {
        outputHandler = new ConsoleOutputHandler();
    }

    @AfterEach
    public void onFinish() {
        outputHandler = null;
    }

    @Test
    public void OnGameStart_DefaultState_MessagePrinted() {
        assertDoesNotThrow(outputHandler::onGameStart);
    }

    @Test
    public void InputGameParameters_DefaultState_MessagePrinted() {
        assertDoesNotThrow(outputHandler::inputGameParameters);
    }

    @Test
    public void InvalidGameParameters_DefaultState_MessagePrinted() {
        assertDoesNotThrow(outputHandler::invalidGameParameters);
    }

    @Test
    public void ParametersIsValid_DefaultState_MessagePrinted() {
        assertDoesNotThrow(outputHandler::parametersIsValid);
    }

    @Test
    public void OnGameFinished_DefaultState_MessagePrinted() {
        var info = new PlayerInfo(1, 10, false);
        assertDoesNotThrow(() -> outputHandler.onGameFinished(Collections.emptyList(), info));
    }
}