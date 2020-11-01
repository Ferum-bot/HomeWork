package Classes.Geometry

interface Shape {

    fun perimeter(): Double
    fun area(): Double

    fun isCongruentTo(another: Shape): Boolean
    fun isSimilarTo(another: Shape): Boolean

    fun containsPoint(point: Point):Boolean
    fun rotate(center: Point, angle: Double)
    fun reflex(center: Point)
    fun reflex(axis: Line)
    fun scale(centre: Point, coefficient: Double)
}