package com.ferumbot.jigsaw.client.clients;

import com.ferumbot.jigsaw.client.exception.GameNotStartedException;
import com.ferumbot.jigsaw.client.field.models.FieldParams;
import com.ferumbot.jigsaw.client.field.models.GameField;
import com.ferumbot.jigsaw.client.field.service.GameFieldService;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import com.ferumbot.jigsaw.client.figure.service.GameFigureGenerator;
import com.ferumbot.jigsaw.client.figure.service.GameFigureService;
import com.ferumbot.jigsaw.client.game.GameStatistics;

class DefaultJigsawClient implements JigsawGameClient {

    private boolean isStarted = false;

    private GameField gameField;

    private int turnsCount = 0;

    private final GameFigureGenerator figureGenerator = new GameFigureGenerator();

    @Override
    public void startGame() {
        isStarted = true;
        turnsCount = 0;
        gameField = new GameField();
    }

    @Override
    public void startGame(FieldParams fieldParams) {
        isStarted = true;
        turnsCount = 0;
        gameField = new GameField(fieldParams);
    }

    @Override
    public GameStatistics finishGame() {
        isStarted = false;
        gameField = null;

        return new GameStatistics(turnsCount);
    }

    @Override
    public boolean addFigureToField(GameFigure figure, Coordinates targetCoordinates) {
        if (!isStarted) {
            throw new GameNotStartedException();
        }
        return gameField.addFigure(figure, targetCoordinates);
    }

    @Override
    public void tryToAddFigureToField(GameFigure figure, Coordinates targetCoordinates) {
        if (!isStarted) {
            throw new GameNotStartedException();
        }
        gameField.tryToAddFigure(figure, targetCoordinates);
    }

    @Override
    public GameFigure generateRandomGameFigure() {
        if (!isStarted) {
            throw new GameNotStartedException();
        }
        return figureGenerator.generateRandomFigure();
    }

    @Override
    public GameField getGameField() {
        if (!isStarted) {
            throw new GameNotStartedException();
        }
        return gameField;
    }
}
