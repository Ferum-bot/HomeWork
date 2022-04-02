package com.ferumbot.jigsaw.ui.views;

import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import javafx.scene.layout.Pane;

public class FigureView extends Pane {

    private final GameFigure gameFigure;

    public FigureView(GameFigure gameFigure) {
        this.gameFigure = gameFigure;
    }
}
