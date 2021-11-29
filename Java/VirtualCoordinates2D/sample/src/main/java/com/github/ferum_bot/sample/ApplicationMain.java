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
        var space = ApiManager.defaultSpace();
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 1));
        var point3 = ApiManager.pointOf(new Coord2D(12.2, -124));
        var point4 = ApiManager.pointOf(new Coord2D(12.23, -0.123));
        var origin1 = ApiManager.originOf(new Coord2D(1.5, -1.5));
        var origin2 = ApiManager.originOf(new Coord2D(0.002, 0));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addOrigin(origin1);
        space.addOrigin(origin2);
        origin1.addPoint(point3);
        origin1.addPoint(point4);
        origin2.addPoint(point3);
        origin2.addPoint(point1);

        var bounds = space.getBounds();

        System.out.println("First sample bounds -> \n" + bounds);
    }

    private static void secondSample() {
        var space = ApiManager.spaceOf(new Coord2D(0.1, -0.1));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 1));
        var origin1 = ApiManager.originOf(new Coord2D(-0.2, 55));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addOrigin(origin1);
        origin1.addPoint(point1);

        var serializedGraph = DAGUtils.exportAsString(space);
        var deserializedGraph = DAGUtils.importFromString(serializedGraph);

        System.out.println("Second sample serialized graph -> \n" + serializedGraph);
        System.out.println("Second sample deserialized graph -> \n" + deserializedGraph);
    }

    private static void thirdSample() {
        var space = ApiManager.defaultSpace();
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 1));
        var point3 = ApiManager.pointOf(new Coord2D(2, 2));
        var point4 = ApiManager.pointOf(new Coord2D(3, 3));
        var origin1 = ApiManager.originOf(new Coord2D(4, 4));
        var origin2 = ApiManager.originOf(new Coord2D(5, 5));

        space.addOrigin(origin1);
        space.addPoint(point1);
        space.addPoint(point2);
        space.addPoint(point3);
        space.addPoint(point4);
        origin1.addPoint(point3);
        origin1.addPoint(point4);
        origin1.addOrigin(origin2);
        origin2.addPoint(point1);
        origin2.addPoint(point2);

        var spaceChildren = space.getChildren();
        System.out.println("Third sample space children -> \n" + spaceChildren);

        var origin1Children = origin1.getChildren();
        System.out.println("Third sample origin1 children -> \n" + origin1Children);

        space.removePoint(point1);
        space.removePoint(point2);

        var spaceNewChildren = space.getChildren();
        System.out.println("Third sample space children after remove -> \n" + spaceNewChildren);
    }
}
