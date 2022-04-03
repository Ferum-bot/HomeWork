package com.ferumbot.jigsaw.ui.controller;

import javafx.scene.Node;

public class DragController {

    private final Node node;

    private double mouseX;
    private double mouseY;

    public DragController(Node node) {
        this.node = node;
    }

    public void enableWith(Node node) {
        node.setOnMousePressed(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });

        node.setOnMouseDragged(event -> {
            var newX = event.getSceneX() - mouseX;
            var newY = event.getSceneY() - mouseY;

            node.setLayoutX(newX);
            node.setLayoutY(newY);
        });
    }

    public void disableWith(Node node) {
        node.setOnMousePressed(null);
        node.setOnMouseDragged(null);
    }
}
