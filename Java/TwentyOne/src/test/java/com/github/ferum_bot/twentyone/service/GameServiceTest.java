package com.github.ferum_bot.twentyone.service;

import com.github.ferum_bot.twentyone.models.game.GameParameters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @Test
    public void StartGame_ValidParameters_GameFinished() {
        var parameters = new GameParameters(0, 0);
        var service = new GameService(parameters);

        assertDoesNotThrow(service::startGame);
    }

    @Test
    public void GetWinner_ValidParameters_WinnerReceived() {
        var parameters = new GameParameters(3, 1);
        var service = new GameService(parameters);

        service.startGame();

        var actualWinner = service.getWinner();

        assertDoesNotThrow(service::getWinner);
        assertDoesNotThrow(actualWinner::score);
        assertDoesNotThrow(actualWinner::isCheater);
        assertDoesNotThrow(actualWinner::playerNumber);
    }

    @Test
    public void GetPlayersInfo_ValidParameters_InfoReceived() {
        var parameters = new GameParameters(5, 2);
        var service = new GameService(parameters);

        service.startGame();

        var actualInfo = service.getPlayersInfo();

        assertDoesNotThrow(service::getPlayersInfo);
        assertEquals(7, actualInfo.size());
    }
}