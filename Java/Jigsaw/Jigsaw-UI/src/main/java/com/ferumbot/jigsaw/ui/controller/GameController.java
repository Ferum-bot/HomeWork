package com.ferumbot.jigsaw.ui.controller;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;
import com.ferumbot.jigsaw.core.base.BaseTimeFormatter;
import com.ferumbot.jigsaw.ui.adapters.GameAdapter;
import com.ferumbot.jigsaw.ui.settings.ViewsCoordinatesSettings;
import com.ferumbot.jigsaw.ui.settings.ViewsSizeSettings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameController {

    private final GameAdapter gameAdapter;

    private Pane mainLayout;

    private Label scoreLabel;
    private Label timeLabel;

    private Button playButton;
    private Button finishButton;

    private Timeline timeline;

    private int playedTime = 0;

    public GameController() {
        gameAdapter = new GameAdapter(JigsawGameClient.getInstance());
    }

    public Parent configureLayout() {
        createAllViews();
        configureAllViews();
        setAllListeners();
        setUpTimer();

        return mainLayout;
    }

    private void createAllViews() {
        mainLayout = new Pane();

        scoreLabel = new Label("Not Started");
        timeLabel = new Label("Time: --:--:--");

        playButton = new Button("Play");
        finishButton = new Button("Finish");
    }

    private void configureAllViews() {
        playButton.setLayoutX(ViewsCoordinatesSettings.DEFAULT_BUTTON_X);
        playButton.setLayoutY(ViewsCoordinatesSettings.DEFAULT_BUTTON_Y);

        finishButton.setLayoutX(ViewsCoordinatesSettings.DEFAULT_FINISH_BUTTON_X);
        finishButton.setLayoutY(ViewsCoordinatesSettings.DEFAULT_FINISH_BUTTON_Y);

        scoreLabel.setLayoutX(ViewsCoordinatesSettings.DEFAULT_LABEL_X);
        scoreLabel.setLayoutY(ViewsCoordinatesSettings.DEFAULT_LABEL_Y);

        timeLabel.setLayoutX(ViewsCoordinatesSettings.DEFAULT_TIME_LABEL_X);
        timeLabel.setLayoutY(ViewsCoordinatesSettings.DEFAULT_TIME_LABEL_Y);

        mainLayout.getChildren().add(playButton);
        mainLayout.getChildren().add(finishButton);
        mainLayout.getChildren().add(scoreLabel);
        mainLayout.getChildren().add(timeLabel);
    }

    private void setAllListeners() {
        playButton.setOnMouseClicked(event -> {
            gameAdapter.onPlayButtonClicked();
            playedTime = 0;
            timeline.play();
        });

        finishButton.setOnMouseClicked(event -> {
            timeline.stop();
            var statistics = gameAdapter.onFinishButtonClicked();
        });
    }

    private void setUpTimer() {
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            playedTime++;
            var formattedTime = "Time: " + BaseTimeFormatter.formatSeconds(playedTime);
            timeLabel.setText(formattedTime);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
}
