package com.github.ferum_bot.api.internal.util;

import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.BoundBox;
import com.github.ferum_bot.api.models.Coord2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometryUtilTest {

    @Test
    public void MergeBounds_TwoPoints_SuccessMerge() {
        var firstCoordinate = new Coord2D(0.0, 0.0);
        var secondCoordinate = new Coord2D(1.0, 1.0);
        var firstPoint = ApiManager.pointOf(firstCoordinate);
        var secondPoint = ApiManager.pointOf(secondCoordinate);
        var firstBound = firstPoint.getBounds();
        var secondBound = secondPoint.getBounds();

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
            new Coord2D(0.0, 1.0), new Coord2D(1.0, 0.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_TwoSamePoints_SuccessMerge() {
        var firstCoordinate = new Coord2D(1.0, 1.0);
        var secondCoordinate = new Coord2D(1.0, 1.0);
        var firstPoint = ApiManager.pointOf(firstCoordinate);
        var secondPoint = ApiManager.pointOf(secondCoordinate);
        var firstBound = firstPoint.getBounds();
        var secondBound = secondPoint.getBounds();

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
                new Coord2D(1.0, 1.0), new Coord2D(1.0, 1.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_RectangleInsideRectangle_SuccessMerge() {
        var firstTopLeftCoordinate = new Coord2D(-10.0, 10.0);
        var firstBottomRightCoordinate = new Coord2D(10.0, -10.0);
        var secondTopLeftCoordinate = new Coord2D(-5.0, 5.0);
        var secondBottomRightCoordinate = new Coord2D(0.0, 0.0);
        var firstBound = new BoundBox(firstTopLeftCoordinate, firstBottomRightCoordinate);
        var secondBound = new BoundBox(secondTopLeftCoordinate, secondBottomRightCoordinate);

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
                new Coord2D(-10.0, 10.0), new Coord2D(10.0, -10.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_RectangleCrossRectangle_SuccessMerge() {
        var firstTopLeftCoordinate = new Coord2D(-10.0, 10.0);
        var firstBottomRightCoordinate = new Coord2D(10.0, -10.0);
        var secondTopLeftCoordinate = new Coord2D(-5.0, 15.0);
        var secondBottomRightCoordinate = new Coord2D(20.0, -5.0);
        var firstBound = new BoundBox(firstTopLeftCoordinate, firstBottomRightCoordinate);
        var secondBound = new BoundBox(secondTopLeftCoordinate, secondBottomRightCoordinate);

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
                new Coord2D(-10.0, 15.0), new Coord2D(20.0, -10.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_TwoSameRectangles_SuccessMerge() {
        var firstTopLeftCoordinate = new Coord2D(-10.0, 10.0);
        var firstBottomRightCoordinate = new Coord2D(10.0, -10.0);
        var secondTopLeftCoordinate = new Coord2D(-10.0, 10.0);
        var secondBottomRightCoordinate = new Coord2D(10.0, -10.0);
        var firstBound = new BoundBox(firstTopLeftCoordinate, firstBottomRightCoordinate);
        var secondBound = new BoundBox(secondTopLeftCoordinate, secondBottomRightCoordinate);

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
                new Coord2D(-10.0, 10.0), new Coord2D(10.0, -10.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_DifferentRectangles_SuccessMerge() {
        var firstTopLeftCoordinate = new Coord2D(-2.0, 2.0);
        var firstBottomRightCoordinate = new Coord2D(3.0, -4.0);
        var secondTopLeftCoordinate = new Coord2D(10.0, 15.0);
        var secondBottomRightCoordinate = new Coord2D(20.0, 10.0);
        var firstBound = new BoundBox(firstTopLeftCoordinate, firstBottomRightCoordinate);
        var secondBound = new BoundBox(secondTopLeftCoordinate, secondBottomRightCoordinate);

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
                new Coord2D(-2.0, 15.0), new Coord2D(20.0, -4.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_RectangleAndPoint1_SuccessMerge() {
        var firstTopLeftCoordinate = new Coord2D(-2.0, 2.0);
        var firstBottomRightCoordinate = new Coord2D(3.0, -4.0);
        var pointCoordinate = new Coord2D(0, 0);
        var firstBound = new BoundBox(firstTopLeftCoordinate, firstBottomRightCoordinate);
        var secondBound = new BoundBox(pointCoordinate, pointCoordinate);

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
                new Coord2D(-2.0, 2.0), new Coord2D(3, -4.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_RectangleAndPoint2_SuccessMerge() {
        var firstTopLeftCoordinate = new Coord2D(-2.0, 2.0);
        var firstBottomRightCoordinate = new Coord2D(3.0, -4.0);
        var pointCoordinate = new Coord2D(10, 10);
        var firstBound = new BoundBox(firstTopLeftCoordinate, firstBottomRightCoordinate);
        var secondBound = new BoundBox(pointCoordinate, pointCoordinate);

        var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
        var expectedMergedBounds = new BoundBox(
                new Coord2D(-2.0, 10.0), new Coord2D(10.0, -4.0)
        );

        assertEquals(expectedMergedBounds.topLeftCoordinate(), actualMergedBounds.topLeftCoordinate());
        assertEquals(expectedMergedBounds.bottomRightCoordinate(), actualMergedBounds.bottomRightCoordinate());
        assertEquals(expectedMergedBounds, actualMergedBounds);

        var actualTopX = actualMergedBounds.topLeftCoordinate().x();
        var actualTopY = actualMergedBounds.topLeftCoordinate().y();
        var actualBottomX = actualMergedBounds.bottomRightCoordinate().x();
        var actualBottomY = actualMergedBounds.bottomRightCoordinate().y();

        var expectedTopX = expectedMergedBounds.topLeftCoordinate().x();
        var expectedTopY = expectedMergedBounds.topLeftCoordinate().y();
        var expectedBottomX = expectedMergedBounds.bottomRightCoordinate().x();
        var expectedBottomY = expectedMergedBounds.bottomRightCoordinate().y();

        assertEquals(expectedTopX, actualTopX);
        assertEquals(expectedTopY, actualTopY);
        assertEquals(expectedBottomX, actualBottomX);
        assertEquals(expectedBottomY, actualBottomY);
    }

    @Test
    public void MergeBounds_NullCoordinates_ThrowException() {
        Coord2D firstTopLeftCoordinate = null;
        Coord2D firstBottomRightCoordinate = null;
        Coord2D secondTopLeftCoordinate = null;
        Coord2D secondBottomRightCoordinate = null;

        assertThrows(NullPointerException.class, () -> {
            var firstBound = new BoundBox(firstTopLeftCoordinate, firstBottomRightCoordinate);
            var secondBound = new BoundBox(secondTopLeftCoordinate, secondBottomRightCoordinate);

            var actualMergedBounds = GeometryUtil.mergeBounds(firstBound, secondBound);
            var expectedMergedBounds = new BoundBox(
                    new Coord2D(-10.0, 10.0), new Coord2D(10.0, -10.0)
            );
        });
    }

}