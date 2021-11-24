package com.github.ferum_bot.api.models;

import java.io.Serializable;

/**
 * Coordinates holder. Contains x and y coordinates.
 * @author matvejpopov
 * @version 1.0.0
 * @see Serializable
 */
public record Coord2D(
    double x,
    double y
) implements Serializable {

    /**
     * Provides default(initial) coordinates.
     * Initial: (0,0)
     * @return default coordinate.
     */
    public static Coord2D initial() {
        return new Coord2D(0.0, 0.0);
    }
}

