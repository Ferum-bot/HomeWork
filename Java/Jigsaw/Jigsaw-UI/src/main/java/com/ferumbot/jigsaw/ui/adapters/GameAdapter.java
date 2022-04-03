package com.ferumbot.jigsaw.ui.adapters;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.stuff.GameStatistics;
import com.ferumbot.jigsaw.ui.views.FieldView;
import com.ferumbot.jigsaw.ui.views.FigureView;

public class GameAdapter {

    private final JigsawGameClient gameClient;

    public GameAdapter(JigsawGameClient gameClient) {
        this.gameClient = gameClient;
    }

    public void onPlayButtonClicked() {
        gameClient.startGame();
    }

    public boolean addFigureToGameField(FigureView figureView, Coordinates figureBlockCoordinates, Coordinates fieldCoordinates) {
        System.out.println(figureBlockCoordinates);
        System.out.println(fieldCoordinates);
        return false;
    }

    public FigureView getNewFigureView() {
        var gameFigure = gameClient.generateRandomGameFigure();
        return new FigureView(gameFigure);
    }

    public GameStatistics onFinishButtonClicked() {
        return gameClient.finishGame();
    }
}
