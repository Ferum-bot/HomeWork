package com.github.ferum_bot.api.manager;

import com.github.ferum_bot.api.exception.NullCoordinatesException;
import com.github.ferum_bot.api.internal.models.OriginImpl;
import com.github.ferum_bot.api.internal.models.PointImpl;
import com.github.ferum_bot.api.internal.models.SpaceImpl;
import com.github.ferum_bot.api.models.Coord2D;
import com.github.ferum_bot.api.models.Origin;
import com.github.ferum_bot.api.models.Point;
import com.github.ferum_bot.api.models.Space;

/**
 * Main api class. Needed to create base api entities.
 * Use only it to create entities.
 * @see Point
 * @see Origin
 * @see Space
 * @author matvejpopov
 * @version 1.0.0
 */
public class ApiManager {

    /**
     * Creates point with these coordinates.
     * Use only this method to create Point entity.
     * @param coordinates With which coordinates create point.
     * @return Point with these coordinates.
     * @see Point
     * @see Coord2D
     * @throws NullCoordinatesException if the transmitted coordinates is null.
     */
    public static Point pointOf(Coord2D coordinates) {
        if (coordinates == null) {
            throw new NullCoordinatesException("Coordinates can't be null!");
        }

        return new PointImpl(coordinates);
    }

    /**
     * Creates origin with these coordinates.
     * Use only this method to create Origin entity.
     * @param coordinates With which coordinates create origin.
     * @return Origin with these coordinates.
     * @see Origin
     * @see Coord2D
     * @throws NullCoordinatesException if the transmitted coordinates is null.
     */
    public static Origin originOf(Coord2D coordinates) {
        if (coordinates == null) {
            throw new NullCoordinatesException("Coordinates can't be null!");
        }

        return new OriginImpl(coordinates);
    }

    /**
     * Creates space with these coordinates.
     * Use only this method to create Space entity.
     * @param coordinates With which coordinates create origin.
     * @return Space with these initial coordinates.
     * @see Space
     * @see Coord2D
     * @throws NullCoordinatesException if the transmitted coordinates is null.
     */
    public static Space spaceOf(Coord2D coordinates) {
        if (coordinates == null) {
            throw new NullCoordinatesException("Coordinates can't be null!");
        }

        return new SpaceImpl(coordinates);
    }

    /**
     * Creates space with default coordinates. The coordinates are (0, 0).
     * Use only this method to create Space entity.
     * @return Space with default (0, 0) coordinates.
     * @see Space
     * @see Coord2D
     */
    public static Space defaultSpace() {
        return new SpaceImpl(Coord2D.initial());
    }
}
