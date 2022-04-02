package com.ferumbot.jigsaw.client.clients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JigsawGameClientTest {

    private JigsawGameClient client;

    @BeforeEach
    void setUp() {
        client = JigsawGameClient.getInstance();
    }

    @Test
    void StartGame_DefaultState_GameStarts() {

    }

}