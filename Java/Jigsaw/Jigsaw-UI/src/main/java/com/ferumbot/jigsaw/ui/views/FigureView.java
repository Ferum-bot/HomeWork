package com.ferumbot.jigsaw.ui.views;

import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import com.ferumbot.jigsaw.core.base.BaseIdGenerator;
import com.ferumbot.jigsaw.ui.controller.DragController;
import com.ferumbot.jigsaw.ui.settings.ViewsColorSettings;
import com.ferumbot.jigsaw.ui.settings.ViewsSizeSettings;
import javafx.scene.layout.Pane;

import java.util.function.BiConsumer;

public class FigureView extends Pane {

    private final GameFigure gameFigure;

    private final DragController dragController;

    private BiConsumer<Coordinates, Coordinates> figureBlockAndLayoutCoordinatesAction;

    public FigureView(GameFigure gameFigure) {
        super();
        this.gameFigure = gameFigure;
        this.dragController = new DragController(this);
        createFigure();
    }

    public void enableDragAndDrop() {
        dragController.enable();
    }

    public void disableDragAndDrop() {
        dragController.disable();
    }

    public void setPosition(double xCoordinate, double yCoordinate) {
        var xPosition = xCoordinate * ViewsSizeSettings.FIGURE_CELL_WIDTH;
        var yPosition = yCoordinate * ViewsSizeSettings.FIGURE_CELL_HEIGHT;
        setLayoutX(xPosition);
        setLayoutY(yPosition);
    }

    public void setOnFigureReleased(BiConsumer<Coordinates, Coordinates> figureBlockAndLayoutCoordinatesAction) {
        this.figureBlockAndLayoutCoordinatesAction = figureBlockAndLayoutCoordinatesAction;
    }

    public GameFigure getGameFigure() {
        return gameFigure;
    }

    private void createFigure() {
        var blocks = gameFigure.getFigureBlocks();
        blocks.forEach(block -> {
            var blockView = createBlock(block.xCoordinate(), block.yCoordinate());
            getChildren().add(blockView);
        });
    }

    private BlockView createBlock(int relativeX, int relativeY) {
        var block = new BlockView();
        var translateX = relativeX * ViewsSizeSettings.FIGURE_CELL_WIDTH;
        var translateY = relativeY * ViewsSizeSettings.FIGURE_CELL_HEIGHT;

        block.setId(BaseIdGenerator.generateId(relativeX, relativeY));
        block.setTranslateX(translateX);
        block.setTranslateY(translateY);
        block.setColor(ViewsColorSettings.DEFAULT_CELL_FIGURE_COLOR);
        block.setStrokeColor(ViewsColorSettings.DEFAULT_STROKE_COLOR);

        block.setOnMouseReleased(event -> {
            var figureBlockCoordinates = new Coordinates(relativeX, relativeY);
            var layoutCoordinates = getTargetLayoutCoordinates(event.getSceneX(), event.getSceneY());

            if (figureBlockAndLayoutCoordinatesAction != null) {
                figureBlockAndLayoutCoordinatesAction.accept(figureBlockCoordinates, layoutCoordinates);
            }
        });

        return block;
    }

    private Coordinates getTargetLayoutCoordinates(double x, double y) {
        var newX = x / ViewsSizeSettings.FIELD_CELL_WIDTH;
        var newY = y / ViewsSizeSettings.FIELD_CELL_HEIGHT;
        return new Coordinates((int) newX, (int) newY);
    }
}
