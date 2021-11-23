package com.github.ferum_bot.models;

import java.io.Serializable;

public record BoundBox(
    Coord2D topLeftCoordinate,
    Coord2D bottomRightCoordinate
) implements Serializable {
}