package com.github.ferum_bot.twentyone.factories;

import com.github.ferum_bot.twentyone.factories.impl.ThreadPlayerFactory;
import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.models.game.GameTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {

    private PlayerFactory factory;

    @BeforeEach
    public void onStart() {
        factory = new ThreadPlayerFactory();
    }

    @AfterEach
    public void onFinish() {
        factory = null;
    }

    @Test
    public void CreatePlayersWith_SomeParametersAndTable_PlayersCreated() {
        var parameters = new GameParameters(1, 1);
        var table = new GameTable();

        var actualPlayers = factory.createPlayersWith(parameters, table);

        assertEquals(2, actualPlayers.size());
    }

    @Test
    public void CreatePlayersWith_SomeParametersAndTable_EmptyPlayers() {
        var parameters = new GameParameters(0, 0);
        var table = new GameTable();

        var actualPlayers = factory.createPlayersWith(parameters, table);

        assertEquals(0, actualPlayers.size());
    }

    @Test
    public void CreateCommonPlayer_SomeTable_PlayerCreated() {
        var table = new GameTable();

        var commonPlayer = factory.createCommonPlayer(table);

        assertFalse(commonPlayer.getInformation().isCheater());
    }

    @Test
    public void CreateCheaterPlayer_SomeTable_PlayerCreated() {
        var table = new GameTable();

        var commonPlayer = factory.createCheaterPlayer(table);

        assertTrue(commonPlayer.getInformation().isCheater());
    }
}