package com.ferumbot.jigsaw.ui.adapters;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;
import com.ferumbot.jigsaw.client.field.core.FieldConstants;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.stuff.GameStatistics;
import com.ferumbot.jigsaw.ui.views.FieldView;
import com.ferumbot.jigsaw.ui.views.FigureView;

import static com.ferumbot.jigsaw.client.field.core.FieldConstants.DEFAULT_FIELD_SIZE;

public class GameAdapter {

    private final JigsawGameClient gameClient;

    public GameAdapter(JigsawGameClient gameClient) {
        this.gameClient = gameClient;
    }

    public void onPlayButtonClicked() {
        gameClient.startGame();
    }

    public boolean addFigureToGameField(
        FigureView figureView, FieldView fieldView, Coordinates figureBlockCoordinates, Coordinates fieldCoordinates
    ) {
        if (!isOnField(fieldView, figureBlockCoordinates, fieldCoordinates)) {
            return false;
        }

        var figure = figureView.getGameFigure();
        var targetCoordinates = mapToClientCoordinates(fieldView, figureBlockCoordinates, fieldCoordinates);
        return gameClient.addFigureToField(figure, targetCoordinates);
    }

    public FigureView getNewFigureView() {
        var gameFigure = gameClient.generateRandomGameFigure();
        return new FigureView(gameFigure);
    }

    public GameStatistics onFinishButtonClicked() {
        return gameClient.finishGame();
    }

    private boolean isOnField(FieldView fieldView, Coordinates figureBlockCoordinates, Coordinates fieldCoordinates) {
        return fieldCoordinates.xCoordinate() >= 0 && fieldCoordinates.yCoordinate() >= 0 &&
                fieldCoordinates.xCoordinate() < DEFAULT_FIELD_SIZE && fieldCoordinates.yCoordinate() < DEFAULT_FIELD_SIZE;
    }

    private Coordinates mapToClientCoordinates(
        FieldView fieldView, Coordinates figureBlockCoordinates, Coordinates fieldCoordinates
    ) {
        return fieldCoordinates;
    }
}
