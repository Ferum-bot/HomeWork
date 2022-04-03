package com.ferumbot.jigsaw.ui.views;

import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.core.base.BaseIdGenerator;
import com.ferumbot.jigsaw.ui.settings.ViewsColorSettings;
import com.ferumbot.jigsaw.ui.settings.ViewsSizeSettings;
import javafx.scene.layout.Pane;

import java.util.List;

public class FieldView extends Pane {

    private int width = 0;
    private int height = 0;

    public FieldView(int width, int height, double widthSize, double heightSize) {
        super();
        this.width = width;
        this.height = height;
        setPrefSize(widthSize, heightSize);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var block = new BlockView();
                var xPosition = i * ViewsSizeSettings.FIELD_CELL_WIDTH;
                var yPosition = j * ViewsSizeSettings.FIELD_CELL_HEIGHT;

                block.setId(BaseIdGenerator.generateId(i, j));
                block.setStrokeColor(ViewsColorSettings.DEFAULT_STROKE_COLOR);
                block.setTranslateX(xPosition);
                block.setTranslateY(yPosition);

                getChildren().add(block);
            }
        }
    }

    public void addFigure(List<Coordinates> figuresCoordinates) {
        figuresCoordinates.forEach(coordinates -> {
            var pos = coordinates.xCoordinate() * width + coordinates.yCoordinate();
            var block = (BlockView) (getChildren().get(pos));
            block.setColor(ViewsColorSettings.DEFAULT_CELL_FIELD_COLOR);
        });
    }

    public void clear() {
        var cellsCount = width * height;
        for (int i = 0; i < cellsCount; i++) {
            var block = (BlockView) (getChildren().get(i));
            block.setColor(ViewsColorSettings.DEFAULT_EMPTY_CELL_COLOR);
        }
    }
}
