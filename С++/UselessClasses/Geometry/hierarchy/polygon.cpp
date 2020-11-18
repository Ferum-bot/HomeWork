#include "polygon.h"

const long double Polygon::PI = 3.1415926;
const long double Polygon::EPS = 1e-5;
const long double Polygon::INF = 1e9 + 7;


Polygon::Polygon(const Polygon& polygon) = default;

Polygon::~Polygon() = default;

Polygon::Polygon(const std::vector<Point>& vertices) {
    if (vertices.size() < 3) {
        throw std::invalid_argument("Invalid number of vertices");
    }
    this->vertices = vertices;
}

size_t Polygon::verticesCount() const {
    return vertices.size();
}

std::vector<Point> Polygon::getVertices() const {
    return vertices;
}

bool Polygon::isConvex() const {
    size_t size = vertices.size();
    int type = -1;
    for (size_t i = 0; i < size; i++) {
        std::pair<Vector, Vector> resultVectors = Vector::getTwoVectorsFromPoints(vertices[i], vertices[(i + 1) % size], vertices[(i + 2) % size]);
        const Vector& v1 = resultVectors.first;
        const Vector& v2 = resultVectors.second;
        const long double resultOfCrossProduct = Vector::crossProduct(v1, v2);
        if (!Polygon::isCorrectValue(type, resultOfCrossProduct)) {
            return false;
        }
    }
    if (type == -1) {
        return false;
    }
    return true;
}

bool Polygon::isCorrectValue(int& type, const long double& value) {
    if (Polygon::isEqualToZero(value)) {
        return true;
    }
    if (type == -1) {
        type = Polygon::isAboveZero(value);
    }
    else {
        if (type != Polygon::isAboveZero(value)) {
            return false;
        }
    }
    return true;
}

bool Polygon::isEqualToZero(const long double& value) {
    return abs(value) <= Polygon::EPS;
}

bool Polygon::isAboveZero(const long double& value) {
    return value > Polygon::EPS;
}

bool Polygon::isLessZero(const long double& value) {
    return value < -Polygon::EPS;
}

long double Polygon::perimeter() const {
    size_t size = vertices.size();
    long double result = 0;
    for (size_t i = 0; i < size; i++) {
        result += Point::getDist(vertices[i], vertices[(i + 1) % size]);
    }
    return result;
}

long double Polygon::area() const {
    size_t size = vertices.size();
    long double result = 0;
    for (size_t i = 0; i < size; i++) {
        const Vector v1 = vertices[i];
        const Vector v2 = vertices[(i + 1) % size];
        result += Vector::crossProduct(v1, v2);
    }
    return abs(result) / 2;
}

bool Polygon::isCongruentTo(const Shape& another) const {

}

bool Polygon::isSimilarTo(const Shape& another) const {
    const Polygon* current = dynamic_cast<const Polygon*>(&another);
    if (current == nullptr) {
        return false;
    }
    if (this->verticesCount() != current->verticesCount()) {
        return false;
    }
    std::vector<Segment> sidesOfFirstPolygon = {};
    std::vector<Segment> sidesOfSecondPolygon = {};
    size_t size = this->verticesCount();
    for (size_t i = 0; i < size; i++) {
        Segment segFirst(vertices[i], vertices[(i + 1) % size]);
        Segment segSecond(current->vertices[i], current->vertices[(i + 1) % size]);
        sidesOfFirstPolygon.push_back(segFirst);
        sidesOfSecondPolygon.push_back(segSecond);
    }
    for (size_t i = 0; i < size; i++) {
        if (Polygon::isPropotional(sidesOfFirstPolygon, sidesOfSecondPolygon) &&
            Polygon::isAglesEqual(sidesOfFirstPolygon, sidesOfSecondPolygon)) {
            
            return true;
        }
        Polygon::shiftPolygon(sidesOfSecondPolygon);
    }
    return false;
}

bool Polygon::containsPoint(const Point& point) const {
    Line lineWhichContainsCurrentPoint(Point(Polygon::INF, Polygon::INF), point);
    size_t size = vertices.size();
    size_t numberOfIntersections = 0;
    for (size_t i = 0; i < size; i++) {
        const Segment currentSegment(vertices[i], vertices[(i + 1) % size]);
        if (lineWhichContainsCurrentPoint.intersectsWithSegment(currentSegment)) {
            numberOfIntersections++;
        }
    }
    if (numberOfIntersections % 2 == 0) {
        return false;
    }
    return true;
}

void Polygon::rotate(const Point& center, const long double& angle) {
    size_t size = vertices.size();
    for (size_t i = 0; i < size; i++) {
        vertices[i].rotate(center, angle);
    }
    return;
}

void Polygon::reflex(const Point& center) {
    size_t size = vertices.size();
    for (size_t i = 0; i < size; i++) {
        vertices[i].reflex(center);
    }
    return;
}

void Polygon::reflex(const Line& axis) {
    
}

void Polygon::scale(const Point& center, long double& coefficient) {

}

bool Polygon::operator == (const Shape& another) const {
    const Polygon* right = dynamic_cast<const Polygon*>(&another);
    if (right == nullptr) {
        return false;
    }
    if (right->vertices.size() != this->verticesCount()) {
        return false;
    }
    size_t size = this->verticesCount();
    for (size_t i = 0; i < size; i++) {
        if (vertices[i] != right->vertices[i]) {
            return false;
        }
    }
    return true;
}

bool Polygon::operator != (const Shape& another) const {
    return !(*this == another);
}

bool Polygon::isPropotional(const std::vector<Segment>& first, const std::vector<Segment>& second) {
    size_t size = first.size();
    const long double value = first[0].getLength() / second[0].getLength();
    for (size_t i = 0; i < size; i++) {
        const long double currentValue = first[i].getLength() / second[i].getLength();
        if (Point::isEqual(value, currentValue)) {
            continue;
        }
        return false;
    }
    return true;
}

bool Polygon::isAglesEqual(const std::vector<Segment>& first, const std::vector<Segment>& second) {
    size_t size = first.size();
    for (size_t i = 0; i < size; i++) {
        Vector firstVectorFromFirst = first[i].getVector();
        Vector secondVectorFromFirst = first[(i + 1) % size].getVector();
        long double firstAngle = Vector::getAngle(firstVectorFromFirst, secondVectorFromFirst);
        Vector firstVectorFromSecond = second[i].getVector();
        Vector secondVectorFromSecond = second[(i + 1) % size].getVector();
        long double secondAngle = Vector::getAngle(firstVectorFromSecond, secondVectorFromSecond);
        if (Point::isEqual(firstAngle, secondAngle)) {
            continue;
        }
        return false;
    }
    return true;
}

void Polygon::shiftPolygon(std::vector<Segment>& polygon) {
    size_t size = polygon.size();
    std::vector<Segment> result = {};
    for (size_t i = 1; i < size; i++) {
        result.push_back(polygon[i]);
    }
    result.push_back(polygon.front());
    polygon = result;
    return;
}