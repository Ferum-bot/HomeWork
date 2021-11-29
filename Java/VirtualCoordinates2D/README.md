# Virtual Coordinates API

This is the library tha allows creating and modifying DAGs(Directed Acyclic Graphs) using the presented
and implemented API for creating entities and manipulating them.

All library usage starts with `ApiManager` class, that provides base methods for creating
api entities.

API also supports DAGs serialization and deserialization. 
For more information [see](./api/src/main/java/com/github/ferum_bot/api/util/DAGUtils.java) `DAGUtils` class.

### Available Entities:
* Point
* Origin
* Space

## Point:

First base api class. Represents the physical point with some position.
Can move by changing its position.

Example:
```java
public void pointExample() {
    var position = new Coord2D(15, 11);
    var point = ApiManager.pointOf(position);
    var bounds = point.getBounds();
    
    System.out.println(bounds);
    System.out.println(point.getPosition());
}
```
```text
Output:
BoundBox[topLeftCoordinate=Coord2D[x=15.0, y=11.0], bottomRightCoordinate=Coord2D[x=15.0, y=11.0]]
Coord2D[x=15.0, y=11.0]
```

[Click](./api/src/main/java/com/github/ferum_bot/api/models/Point.java) for interface documentation.

## Origin:

Each coordinate system is represented by its origin point.
Entity has property `position` that shows the offset of this origin relative to some other coordinate system.
Like a physical point, Origin can move by changing its position.

Example:
```java
public void originExample() {
    var initialOffset = new Coord2D(1, 1);
    var origin = ApiManager.originOf(initialOffset);
    var somePoint = ApiManager.pointOf(new Coord2D(5, -3));
    origin.addPoint(somePoint);

    var originBounds = origin.getBounds();
    var pointBounds = somePoint.getBounds();

    System.out.println("Origin bounds: \n" + originBounds);
    System.out.println("Point bounds: \n" + pointBounds);
}
```
```text
Output:
Origin bounds -> 
BoundBox[topLeftCoordinate=Coord2D[x=6.0, y=-2.0], bottomRightCoordinate=Coord2D[x=6.0, y=-2.0]]
Point bounds -> 
BoundBox[topLeftCoordinate=Coord2D[x=5.0, y=-3.0], bottomRightCoordinate=Coord2D[x=5.0, y=-3.0]]
```

[Click](./api/src/main/java/com/github/ferum_bot/api/models/Origin.java) for interface documentation.

## Space:

Root parent node of the graph.
Defines the "world coordinate system" for the entire graph.
Also uses to serialization and deserialization.

```java
public void spaceExample() {
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
```
```text
Output:
{
    type: SPACE
    coordinates: (0.0, 0.0)
    children:
    [
        {
            type: POINT
            coordinates: (1.0, 1.0)
        }
        {
            type: ORIGIN
            coordinates: (-1.0, -1.0)
            children:
            [
                {
                    type: POINT
                    coordinates: (1.2, 0.7)
                }
            ]
        }
    ]
}
```

[Click](./api/src/main/java/com/github/ferum_bot/api/models/Space.java) for interface documentation.

## BoundBox:

Some minimum volume of space in which all physical points located relative to a certain
coordinate system in this coordinate system.

More formally, it is a rectangle with sides parallel to the coordinate axes, 
determined by the coordinates of its diagonal (points with minimum and maximum coordinates),
and covering the coordinates of all points bounded by it.

The coordinates are absolute for this coordinate system.

```java
public void boundBoxExample() {
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
```
```text
Output:
Origin bounds -> 
BoundBox[topLeftCoordinate=Coord2D[x=-1.0, y=36.2], bottomRightCoordinate=Coord2D[x=92.2, y=-41.0]]
Point bounds -> 
BoundBox[topLeftCoordinate=Coord2D[x=90.2, y=17.0], bottomRightCoordinate=Coord2D[x=90.2, y=17.0]]

```

[Click](./api/src/main/java/com/github/ferum_bot/api/models/BoundBox.java) for class documentation.

## Samples:

Also, available three samples, that shows base library usage.
1. `firstSample()` - Creating graph and calculating bounds. [See](./sample/src/main/java/com/github/ferum_bot/sample/ApplicationMain.java).
2. `secondSample()` - Creating graph and serializing and deserializing it. [See](./sample/src/main/java/com/github/ferum_bot/sample/ApplicationMain.java).
3. `thirdSample()` - Creating graph, changing position and remove entities. [See](./sample/src/main/java/com/github/ferum_bot/sample/ApplicationMain.java).

## Also see:
* [How serialization work](./HOW_SERIALIZATION_WORK.md)
* [How see coverage](./HOW_SEE_COVERAGE.md)
* [Source code](./api/src/main/java/com/github/ferum_bot/api)
* [All available tests](./api/src/test/java/com/github/ferum_bot/api)

### Created and powered by Matvey Popov
#### Actual version 1.0.0
#### Test coverage 89%
