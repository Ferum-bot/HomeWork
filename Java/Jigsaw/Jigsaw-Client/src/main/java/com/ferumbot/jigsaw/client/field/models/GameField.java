package com.ferumbot.jigsaw.client.field.models;

import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;

import java.util.List;

/**
 * Base game field API interface.
 * @author matvejpopov
 * @version 1.0.0
 * @see GameFigure
 * @see FieldParams
 * @see FieldBlock
 * @see Coordinates
 */
public interface GameField {

    /**
     * Returns field figures.
     * @return current figures on the field.
     * @apiNote List is unmodifiable.
     * @see GameFigure
     */
    List<GameFigure> getFigures();

    /**
     * Returns field parameters.
     * @see FieldParams
     * @return field parameters.
     * @see FieldParams
     */
    FieldParams getParams();

    /**
     * Returns field blocks.
     * @return blocks of current game field.
     * @see FieldBlock
     * @apiNote List is unmodifiable.
     */
    List<FieldBlock> getFieldBlocks();
}
