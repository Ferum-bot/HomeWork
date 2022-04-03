package com.ferumbot.jigsaw.ui.controller;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;
import com.ferumbot.jigsaw.client.field.core.FieldConstants;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.core.base.BaseTimeFormatter;
import com.ferumbot.jigsaw.ui.adapters.GameAdapter;
import com.ferumbot.jigsaw.ui.settings.ViewsCoordinatesSettings;
import com.ferumbot.jigsaw.ui.settings.ViewsSizeSettings;
import com.ferumbot.jigsaw.ui.views.FieldView;
import com.ferumbot.jigsaw.ui.views.FigureView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameController {

    private static final int FIELD_BLOCK_WIDTH = FieldConstants.DEFAULT_FIELD_SIZE;
    private static final int FIELD_BLOCK_HEIGHT = FieldConstants.DEFAULT_FIELD_SIZE;

    private final GameAdapter gameAdapter;

    private Pane mainLayout;

    private Label scoreLabel;
    private Label timeLabel;

    private Button playButton;
    private Button finishButton;

    private Timeline timeline;

    private FieldView fieldView;

    private FigureView currentFigure;

    private int playedTime = 0;
    private int score = 0;

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

        var fieldWidth = ViewsSizeSettings.FIELD_CELL_WIDTH * FIELD_BLOCK_WIDTH;
        var fieldHeight = ViewsSizeSettings.FIELD_CELL_HEIGHT * FIELD_BLOCK_HEIGHT;
        fieldView = new FieldView(FIELD_BLOCK_WIDTH, FIELD_BLOCK_HEIGHT, fieldWidth, fieldHeight);
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

        mainLayout.getChildren().add(fieldView);
        mainLayout.getChildren().add(playButton);
        mainLayout.getChildren().add(finishButton);
        mainLayout.getChildren().add(scoreLabel);
        mainLayout.getChildren().add(timeLabel);
    }

    private void setAllListeners() {
        playButton.setOnMouseClicked(event -> {
            prepareGame();
            var figure = gameAdapter.getNewFigureView();
            addNewFigure(figure);
        });

        finishButton.setOnMouseClicked(event -> {
            releaseGame();
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

    private void figureReleasedCallback(Coordinates figureBlockCoordinate, Coordinates layoutCoordinate) {
        if (figureIsAddedToField(figureBlockCoordinate, layoutCoordinate)) {
            var figure = gameAdapter.getNewFigureView();
            score += figure.getGameFigure().getFigureBlocks().size();
            setScore(score);
            removeCurrentFigure();
            addNewFigure(figure);
        } else {
            moveFigureToStart(currentFigure);
        }
    }

    private boolean figureIsAddedToField(Coordinates figureBlockCoordinate, Coordinates layoutCoordinate) {
        var addResult = gameAdapter.addFigureToGameField(currentFigure, fieldView, figureBlockCoordinate, layoutCoordinate);
        if (addResult) {
            var figure = currentFigure.getGameFigure();
            var coordinates = gameAdapter.mapToUICoordinates(figure, layoutCoordinate, figureBlockCoordinate);
            fieldView.addFigure(coordinates);
        }
        return addResult;
    }

    private void prepareGame() {
        gameAdapter.onPlayButtonClicked();
        playedTime = 0;
        score = 0;
        timeline.play();
        fieldView.clear();
        setScore(score);
    }

    private void releaseGame() {
        timeline.stop();
        currentFigure.disableDragAndDrop();
        moveFigureToStart(currentFigure);
        removeCurrentFigure();
        gameAdapter.onFinishButtonClicked();
    }

    private void addNewFigure(FigureView figureView) {
        currentFigure = figureView;
        currentFigure.enableDragAndDrop();
        moveFigureToStart(currentFigure);
        currentFigure.setOnFigureReleased(this::figureReleasedCallback);

        mainLayout.getChildren().add(currentFigure);
    }

    private void removeCurrentFigure() {
        mainLayout.getChildren().remove(currentFigure);
    }

    private void moveFigureToStart(FigureView figureView) {
        figureView.setPosition(ViewsCoordinatesSettings.INIT_FIGURE_X, ViewsCoordinatesSettings.INIT_FIGURE_Y);
    }

    private void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
