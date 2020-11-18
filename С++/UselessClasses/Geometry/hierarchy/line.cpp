#include "line.h"


const long double Line::PI = 3.1415926;
const long double Line::EPS = 1e-5;

Line::Line(const Line& line) = default;

Line::~Line() = default;

Line::Line(const Point& start, const Point& finish) {
    const long double x1 = start.getX();
    const long double y1 = start.getY();
    const long double x2 = finish.getX();
    const long double y2 = finish.getY();
    
    a = y1 - y2;
    b = x2 - x1;
    c = x1 * y2 - x2 * y1;
}

Line::Line(const long double& a, const long double& b, const long double& c) {
    this->a = a;
    this->b = b;
    this->c = c;
}

long double Line::getA() const {
    return a;
}

long double Line::getB() const {
    return b;
}

long double Line::getC() const {
    return c;
}

bool operator == (const Line& left, const Line& right) {
    long double deltaA = abs(left.getA() - right.getA());
    long double deltaB = abs(left.getB() - right.getB());
    long double deltaC = abs(left.getC() - right.getC());
    return deltaA < Line::EPS && deltaB < Line::EPS && deltaC < Line::EPS;
}

bool operator != (const Line& left, const Line& right) {
    return !(left == right);
}

bool Line::intersectsWithSegment(const Segment& segment) const {
    const Line line(segment.getFirstPoint(), segment.getSecondPoint());
    if (!this->intersectsWithLine(line)) {
        return false;
    }
    Point intersectionPoint = this->getIntersection(line);
    if (Point::leftIsAboveRight(intersectionPoint.getX(), segment.getMinX()) &&
        Point::leftIsAboveRight(segment.getMaxX(), intersectionPoint.getX()) &&
        Point::leftIsAboveRight(intersectionPoint.getY(), segment.getMinY()) &&
        Point::leftIsAboveRight(segment.getMaxY(), intersectionPoint.getY())) {
        
        return true;

    }
    return false;
}

bool Line::intersectsWithLine(const Line& line) const {
    if (Point::isEqualToZero(a) && Point::isEqualToZero(line.a)) {
        return false;
    }
    if (Point::isEqualToZero(b) && Point::isEqualToZero(line.b)) {
        return false;
    }
    if (Point::isEqualToZero(a) || Point::isEqualToZero(b) || 
        Point::isEqualToZero(line.a) || Point::isEqualToZero(line.b)) {
        return true;
    }
    const long double value = line.a * b - a * line.b;
    if (Point::isEqualToZero(value)) {
        return false;
    }
    return true;
}

Point Line::getIntersection(const Line& line) const {
    if (Point::isEqualToZero(a) && Point::isEqualToZero(line.b)) {
        return Point(-line.c / line.a, -c / b);
    }
    if (Point::isEqualToZero(b) && Point::isEqualToZero(line.a)) {
        return Point( -c / b, -line.c / line.a);
    }
    long double x;
    long double y;
    if (Point::isEqualToZero(a)) {
        y = -c / b;
        x = (-line.c - line.b * y) / line.a;
        return Point(x, y);
    }
    if (Point::isEqualToZero(b)) {
        x = -c / a;
        y = (-line.c - line.a * x) / line.b;
        return Point(x, y);
    }
    if (Point::isEqualToZero(line.a)) {
        y = -line.c / line.b;
        x = (-c - b * y) / a;
        return Point(x, y);
    }
    if (Point::isEqualToZero(line.b)) {
        x = -line.c / line.a;
        y = (-c - a * x) / b;
        return Point(x, y);
    }
    y = (a * line.c - line.a * c) / (line.a * b - a * line.b);
    x = (-line.b * y - line.c) / line.a;
    return Point(x, y);
}