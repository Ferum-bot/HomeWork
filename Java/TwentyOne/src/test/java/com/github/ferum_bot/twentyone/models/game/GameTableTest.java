package com.github.ferum_bot.twentyone.models.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTableTest {

    private final GameTable gameTable = new GameTable();

    @Test
    public void GetCard_DefaultState_CardReceived() {
        assertDoesNotThrow(gameTable::getCard);
    }
}