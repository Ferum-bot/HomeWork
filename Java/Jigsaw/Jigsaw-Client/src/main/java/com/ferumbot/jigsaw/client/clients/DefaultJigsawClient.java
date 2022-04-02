package com.ferumbot.jigsaw.client.clients;

import com.ferumbot.jigsaw.client.exception.GameNotStartedException;
import com.ferumbot.jigsaw.client.exception.UnSupportedClassImplementationException;
import com.ferumbot.jigsaw.client.field.models.FieldParams;
import com.ferumbot.jigsaw.client.field.models.MutableGameField;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import com.ferumbot.jigsaw.client.figure.model.MutableGameFigure;
import com.ferumbot.jigsaw.client.figure.service.GameFigureGenerator;
import com.ferumbot.jigsaw.client.game.GameStatistics;

class DefaultJigsawClient implements JigsawGameClient {

    private boolean isStarted = false;

    private MutableGameField gameField;

    private int turnsCount = 0;

    private final GameFigureGenerator figureGenerator = new GameFigureGenerator();

    @Override
    public void startGame() {
        isStarted = true;
        turnsCount = 0;
        gameField = new MutableGameField();
    }

    @Override
    public void startGame(FieldParams fieldParams) {
        isStarted = true;
        turnsCount = 0;
        gameField = new MutableGameField(fieldParams);
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
        if (figure instanceof MutableGameFigure mutableFigure) {
            return gameField.addFigure(mutableFigure, targetCoordinates);
        }
        throw new UnSupportedClassImplementationException("Unsupported class implementation of GameFigure: " + figure);
    }

    @Override
    public void tryToAddFigureToField(GameFigure figure, Coordinates targetCoordinates) {
        if (!isStarted) {
            throw new GameNotStartedException();
        }
        if (figure instanceof MutableGameFigure mutableFigure) {
            gameField.tryToAddFigure(mutableFigure, targetCoordinates);
        }
        throw new UnSupportedClassImplementationException("Unsupported class implementation of GameFigure: " + figure);
    }

    @Override
    public MutableGameFigure generateRandomGameFigure() {
        if (!isStarted) {
            throw new GameNotStartedException();
        }
        return figureGenerator.generateRandomFigure();
    }

    @Override
    public MutableGameField getGameField() {
        if (!isStarted) {
            throw new GameNotStartedException();
        }
        return gameField;
    }
}
