package com.ferumbot.jigsaw.client.clients;

import com.ferumbot.jigsaw.client.field.models.FieldParams;
import com.ferumbot.jigsaw.client.field.models.GameField;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import com.ferumbot.jigsaw.client.game.GameStatistics;

public interface JigsawGameClient {

    static JigsawGameClient getInstance() {
        return new DefaultJigsawClient();
    }

    void startGame();

    void startGame(FieldParams fieldParams);

    GameStatistics finishGame();

    boolean addFigureToField(GameFigure figure, Coordinates targetCoordinates);

    void tryToAddFigureToField(GameFigure figure, Coordinates targetCoordinates);

    GameFigure generateRandomGameFigure();

    GameField getGameField();
}
