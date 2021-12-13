package com.github.ferum_bot.twentyone.models.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameParametersTest {

    @Test
    public void Constructor_ClassState1_ClassCreated() {
        var parameters = new GameParameters(0, 0);

        assertEquals(0, parameters.numberOfCommonPlayers());
        assertEquals(0, parameters.numberOfCheaters());
    }

    @Test
    public void Constructor_ClassState2_ClassCreated() {
        var parameters = new GameParameters(32, -11);

        assertEquals(32, parameters.numberOfCommonPlayers());
        assertNotEquals(-10, parameters.numberOfCheaters());
    }
}