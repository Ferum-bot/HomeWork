package com.github.ferum_bot.api.internal.util;

import com.github.ferum_bot.api.models.BoundBox;
import com.github.ferum_bot.api.models.Coord2D;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GeometryUtil {

    public static BoundBox mergeBounds(BoundBox first, BoundBox second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        var firstTopLeftX = first.topLeftCoordinate().x();
        var secondTopLeftX = second.topLeftCoordinate().x();

        var firstTopLeftY = first.topLeftCoordinate().y();
        var secondTopLeftY = second.topLeftCoordinate().y();

        var firstBottomRightX = first.bottomRightCoordinate().x();
        var secondBottomRightX = second.bottomRightCoordinate().x();

        var firstBottomRightY = first.bottomRightCoordinate().y();
        var secondBottomRightY = second.bottomRightCoordinate().y();

        var topLeftX = min(firstTopLeftX, secondTopLeftX);
        var topLeftY = max(firstTopLeftY, secondTopLeftY);

        var bottomRightX = max(firstBottomRightX, secondBottomRightX);
        var bottomRightY = min(firstBottomRightY, secondBottomRightY);

        var topLeft = new Coord2D(topLeftX, topLeftY);
        var bottomRight = new Coord2D(bottomRightX, bottomRightY);

        return new BoundBox(topLeft, bottomRight);
    }
}
