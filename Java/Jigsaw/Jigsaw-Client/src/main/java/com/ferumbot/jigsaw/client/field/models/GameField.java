package com.ferumbot.jigsaw.client.field.models;

import com.ferumbot.jigsaw.client.field.exception.FigureNotAddedException;
import com.ferumbot.jigsaw.client.field.models.FieldParams;
import com.ferumbot.jigsaw.client.field.service.GameFieldService;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameField {

    private static final int DEFAULT_FIELD_SIZE = 9;

    private final GameFieldService service;

    private final FieldParams params;

    private final List<GameFigure> figures;

    public GameField() {
        params = new FieldParams(DEFAULT_FIELD_SIZE, DEFAULT_FIELD_SIZE);
        figures = new LinkedList<>();
        service = new GameFieldService();
    }

    public GameField(FieldParams params) {
        this.params = params;
        figures = new LinkedList<>();
        service = new GameFieldService();
    }

    public List<GameFigure> getFigures() {
        return Collections.unmodifiableList(figures);
    }

    public boolean addFigure(GameFigure figure) {
        if (service.canAddNewFigure(this, figure)) {
            return figures.add(figure);
        }
        return false;
    }

    public void tryToAddFigure(GameFigure figure) {
        if (service.canAddNewFigure(this, figure)) {
            figures.add(figure);
        }

        throw new FigureNotAddedException("Can't add figure " + figure.getId());
    }

    public boolean removeFigure(GameFigure figure) {
        return figures.remove(figure);
    }
}
