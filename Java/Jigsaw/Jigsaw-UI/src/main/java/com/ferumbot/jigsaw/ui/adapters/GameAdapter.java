package com.ferumbot.jigsaw.ui.adapters;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;
import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import com.ferumbot.jigsaw.client.stuff.GameStatistics;
import com.ferumbot.jigsaw.ui.views.FieldView;
import com.ferumbot.jigsaw.ui.views.FigureView;

import java.util.List;

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
        var figure = figureView.getGameFigure();
        if (!isOnField(fieldCoordinates, figureBlockCoordinates, figure)) {
            return false;
        }

        var targetCoordinates = mapToClientCoordinates(fieldCoordinates, figureBlockCoordinates, figure);
        return gameClient.addFigureToField(figure, targetCoordinates);
    }

    public FigureView getNewFigureView() {
        var gameFigure = gameClient.generateRandomGameFigure();
        return new FigureView(gameFigure);
    }

    public GameStatistics onFinishButtonClicked() {
        return gameClient.finishGame();
    }

    public List<Coordinates> mapToUICoordinates(
        GameFigure gameFigure, Coordinates layoutCoordinate, Coordinates figureBlockCoordinates
    ) {
        return gameFigure.getFigureBlocks().stream()
            .map(block -> {
                var newX = block.xCoordinate() + layoutCoordinate.xCoordinate() + figureBlockCoordinates.xCoordinate();
                var newY = block.yCoordinate() + layoutCoordinate.yCoordinate() + figureBlockCoordinates.yCoordinate();
                return new Coordinates(newX, newY);
            }).toList();
    }

    private boolean isOnField(
        Coordinates fieldCoordinates, Coordinates figureBlockCoordinates, GameFigure figure
    ) {
        var actualBlocks = figure.getFigureBlocks().stream()
            .map(figureBlock -> {
                var newX = figureBlock.xCoordinate() + fieldCoordinates.xCoordinate() - figureBlockCoordinates.xCoordinate();
                var newY = figureBlock.yCoordinate() + fieldCoordinates.yCoordinate() - figureBlockCoordinates.yCoordinate();
                return new Coordinates(newX, newY);
            }).toList();

        for (Coordinates block: actualBlocks) {
            if (block.xCoordinate() < 0 || block.yCoordinate() < 0) {
                return false;
            }
            if (block.xCoordinate() >= DEFAULT_FIELD_SIZE || block.yCoordinate() >= DEFAULT_FIELD_SIZE) {
                return false;
            }
        }

        return true;
    }

    private Coordinates mapToClientCoordinates(
        Coordinates fieldCoordinates, Coordinates figureBlockCoordinates, GameFigure figure
    ) {
        var blocks = figure.getFigureBlocks();
        FigureBlock minBlock = blocks.get(0);
        for (FigureBlock block: blocks) {
            if (minBlock.xCoordinate() > block.xCoordinate()) {
                minBlock = block;
            }
            if (minBlock.xCoordinate() == block.xCoordinate()) {
                if (minBlock.yCoordinate() > block.yCoordinate()) {
                    minBlock = block;
                }
            }
        }

        var newX = fieldCoordinates.xCoordinate() + minBlock.xCoordinate() - figureBlockCoordinates.xCoordinate();
        var newY = fieldCoordinates.yCoordinate() + minBlock.yCoordinate() - figureBlockCoordinates.yCoordinate();
        return new Coordinates(newX, newY);
    }
}
