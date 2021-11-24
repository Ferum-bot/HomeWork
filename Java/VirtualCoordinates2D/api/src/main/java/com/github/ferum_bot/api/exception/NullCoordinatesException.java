package com.github.ferum_bot.api.exception;

import com.github.ferum_bot.api.models.Coord2D;

/**
 * Represents null coordinates.
 * @author matvejpopov
 * @version 1.0.0
 * @see Coord2D
 */
public class NullCoordinatesException extends IllegalArgumentException {

    public NullCoordinatesException() {
        super();
    }

    public NullCoordinatesException(String message) {
        super(message);
    }
}
