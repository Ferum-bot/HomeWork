package com.github.ferum_bot.sample;

import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.Coord2D;
import com.github.ferum_bot.api.util.DAGUtils;

import java.util.Collection;
import java.util.HashSet;

public class ApplicationMain {

    public static void main(String[] args) {
        pointExample();

        originExample();

        spaceExample();

        boundBoxExample();

        firstSample();

        secondSample();

        thirdSample();
    }

    public static void pointExample() {
        var position = new Coord2D(15, 11);
        var point = ApiManager.pointOf(position);
        var bounds = point.getBounds();

        System.out.println(bounds);
        System.out.println(point.getPosition());
    }

    public static void originExample() {
        var initialOffset = new Coord2D(1, 1);
        var origin = ApiManager.originOf(initialOffset);
        var somePoint = ApiManager.pointOf(new Coord2D(5, -3));
        origin.addPoint(somePoint);

        var originBounds = origin.getBounds();
        var pointBounds = somePoint.getBounds();

        System.out.println("Origin bounds -> \n" + originBounds);
        System.out.println("Point bounds -> \n" + pointBounds);
    }

    public static void spaceExample() {
        var space = ApiManager.defaultSpace();
        var someOrigin = ApiManager.originOf(new Coord2D(-1, -1));
        var firstPoint = ApiManager.pointOf(new Coord2D(1, 1));
        var secondPoint = ApiManager.pointOf(new Coord2D(1.2, 0.7));

        space.addPoint(firstPoint);
        space.addOrigin(someOrigin);
        someOrigin.addPoint(secondPoint);

        var spaceString = DAGUtils.exportAsString(space);

        System.out.println(spaceString);
    }

    public static void boundBoxExample() {
        var someOrigin = ApiManager.originOf(new Coord2D(2, 3));
        var firstPoint = ApiManager.pointOf(new Coord2D(90.2, 17));
        var secondPoint = ApiManager.pointOf(new Coord2D(-3, -44));
        var thirdPoint = ApiManager.pointOf(new Coord2D(0, 33.2));

        someOrigin.addPoint(firstPoint);
        someOrigin.addPoint(secondPoint);
        someOrigin.addPoint(thirdPoint);

        var originBounds = someOrigin.getBounds();
        var firstPointBounds = firstPoint.getBounds();

        System.out.println("Origin bounds -> \n" + originBounds);
        System.out.println("Point bounds -> \n" + firstPointBounds);
    }

    private static void firstSample() {

    }

    private static void secondSample() {

    }

    private static void thirdSample() {

    }
}
