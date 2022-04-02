package com.ferumbot.jigsaw.ui.views;

import com.ferumbot.jigsaw.client.figure.model.MutableGameFigure;
import javafx.scene.layout.Pane;

public class FigureView extends Pane {

    private final MutableGameFigure gameFigure;

    public FigureView(MutableGameFigure gameFigure) {
        this.gameFigure = gameFigure;
    }


}
