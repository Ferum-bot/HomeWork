package com.ferumbot.jigsaw.ui.controller;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;
import com.ferumbot.jigsaw.ui.adapters.GameAdapter;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameController {

    private final GameAdapter gameAdapter;

    private Pane mainLayout;

    private Label scoreLabel;
    private Label timeLabel;

    private Button playButton;
    private Button finishButton;

    private Timeline timeline;

    public GameController() {
        gameAdapter = new GameAdapter(JigsawGameClient.getInstance());
    }

    public Parent configureLayout() {
        createAllViews();
        configureAllViews();
        setAllListeners();
    }

    private void createAllViews() {

    }

    private void configureAllViews() {

    }

    private void setAllListeners() {

    }
}
