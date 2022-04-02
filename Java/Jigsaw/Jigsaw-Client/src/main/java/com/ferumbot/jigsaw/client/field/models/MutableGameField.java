package com.ferumbot.jigsaw.client.field.models;

import com.ferumbot.jigsaw.client.field.exception.FigureNotAddedException;
import com.ferumbot.jigsaw.client.field.service.GameFieldService;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import com.ferumbot.jigsaw.client.figure.model.MutableGameFigure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.ferumbot.jigsaw.client.field.core.FieldConstants.DEFAULT_FIELD_SIZE;

public class MutableGameField implements GameField {

    private final GameFieldService service;

    private final FieldParams params;

    private final List<MutableGameFigure> figures;

    public MutableGameField() {
        params = new FieldParams(DEFAULT_FIELD_SIZE, DEFAULT_FIELD_SIZE);
        figures = new LinkedList<>();
        service = new GameFieldService();
    }

    public MutableGameField(FieldParams params) {
        this.params = params;
        figures = new LinkedList<>();
        service = new GameFieldService();
    }

    @Override
    public List<GameFigure> getFigures() {
        return Collections.unmodifiableList(figures);
    }

    @Override
    public FieldParams getParams() {
        return params;
    }

    @Override
    public List<FieldBlock> getFieldBlocks() {
        return service.generateFieldBlocks(this);
    }

    public List<MutableGameFigure> getMutableFigures() {
        return Collections.unmodifiableList(figures);
    }

    public boolean addFigure(MutableGameFigure figure, Coordinates targetCoordinates) {
        if (service.canAddNewFigure(this, figure, targetCoordinates)) {
            figure.setCoordinates(targetCoordinates);
            return figures.add(figure);
        }
        return false;
    }

    public void tryToAddFigure(MutableGameFigure figure, Coordinates targetCoordinates) {
        if (service.canAddNewFigure(this, figure, targetCoordinates)) {
            figure.setCoordinates(targetCoordinates);
            figures.add(figure);
        }

        throw new FigureNotAddedException("Can't add figure " + figure.getId());
    }

    public boolean removeFigure(MutableGameFigure figure) {
        return figures.remove(figure);
    }

    public Optional<MutableGameFigure> getFigure(int id) {
        return figures.stream()
            .filter(figure -> figure.getId() == id)
            .findFirst();
    }

    public boolean removeFigure(int id) {
        var figureToRemove = figures.stream()
            .filter(figure -> figure.getId() == id)
            .findFirst();
        if (figureToRemove.isEmpty()) {
            return false;
        }
        return removeFigure(figureToRemove.get());
    }
}
