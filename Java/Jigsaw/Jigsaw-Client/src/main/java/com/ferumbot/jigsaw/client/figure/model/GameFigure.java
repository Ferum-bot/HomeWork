package com.ferumbot.jigsaw.client.figure.model;

import com.ferumbot.jigsaw.client.clients.JigsawGameClient;
import com.ferumbot.jigsaw.client.field.models.GameField;
import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;

import java.util.List;
import java.util.Optional;

/**
 * Base game figure API interface.
 * @see GameField
 * @see JigsawGameClient
 * @author matvejpopov
 * @version 1.0.0
 */
public interface GameFigure {

    /**
     * Get's unique figure id.
     * @return unique id.
     */
    int getId();

    /**
     * Сhecks if figure has been added to the field.
     * @return true if figure is added to field and false otherwise.
     */
    boolean isAddedToField();

    /**
     * Returns field coordinates of current figure.
     * @return Coordinates if figure is added to field and empty optional otherwise.
     * @see Coordinates
     */
    Optional<Coordinates> getFieldCoordinates();

    /**
     * Returns the figure blocks.
     * @return the figure blocks.
     * @apiNote List is unmodifiable.
     * @see FigureBlock
     */
    List<FigureBlock> getFigureBlocks();
}
