package com.github.ferum_bot.api.models;

/**
 * Entity bounds holder. Contains the coordinates of the main diagonal.
 * @author matvejpopov
 * @version 1.0.0
 * @see Coord2D
 */
public record BoundBox(
    Coord2D topLeftCoordinate,
    Coord2D bottomRightCoordinate
) { }
