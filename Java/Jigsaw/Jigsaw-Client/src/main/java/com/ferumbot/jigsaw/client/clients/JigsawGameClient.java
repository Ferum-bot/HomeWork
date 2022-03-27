package com.ferumbot.jigsaw.client.clients;

public interface JigsawGameClient {

    static JigsawGameClient getInstance() {
        return new DefaultJigsawClient();
    }

    void startGame();

    void finishGame();
}
