package com.ferumbot.jigsaw.ui.stage;

import com.ferumbot.jigsaw.ui.controller.GameController;
import javafx.stage.Stage;

public class GameStage {

    private final Stage stage;

    private final GameController controller;

    public GameStage(Stage stage, GameController controller) {
        this.stage = stage;
        this.controller = controller;
    }
}
