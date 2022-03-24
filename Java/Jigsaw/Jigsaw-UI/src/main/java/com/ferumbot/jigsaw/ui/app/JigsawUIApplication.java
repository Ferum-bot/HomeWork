package com.ferumbot.jigsaw.ui.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class JigsawUIApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.show();
    }

    public static void launch() {
        Application.launch();
    }
}
