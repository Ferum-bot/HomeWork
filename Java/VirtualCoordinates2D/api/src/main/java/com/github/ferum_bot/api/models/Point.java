package com.github.ferum_bot.api.models;

/**
 * Point entity interface.
 * @author matvejpopov
 * @version 1.0.0
 * @see Coordinatable
 */
public interface Point extends Coordinatable {

    /**
     * Getter for Point bounds. The bound is degenerate.
     * @return Point bounds.
     * @see BoundBox
     */
    BoundBox getBounds();
}
