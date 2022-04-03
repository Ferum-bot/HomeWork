package com.ferumbot.jigsaw.ui.views;

import com.ferumbot.jigsaw.ui.settings.ViewsSizeSettings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlockView extends StackPane {

    private final Rectangle block;

    public BlockView() {
        super();
        block = new Rectangle(ViewsSizeSettings.FIELD_CELL_WIDTH, ViewsSizeSettings.FIELD_CELL_HEIGHT);

        block.setFill(null);
        getChildren().add(block);
    }

    public void setStrokeColor(Color color) {
        block.setStroke(color);
    }

    public void setColor(Color color) {
        block.setFill(color);
    }
}
