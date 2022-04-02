package com.ferumbot.jigsaw.ui.app;

import com.ferumbot.jigsaw.ui.controller.GameController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JigsawUIApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(buildUI()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void launch() {
        Application.launch();
    }

    private Parent buildUI() {
        var controller = new GameController();
        return controller.configureLayout();
    }
}
