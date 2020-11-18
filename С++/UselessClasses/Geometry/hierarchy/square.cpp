#include "square.h"

Square::Square() {}

Square::Square(const Square& square) = default;

Square::~Square() = default;

Square::Square(const Point& first, const Point& second) {
    vertices.push_back(first);
    vertices.push_back(Point(first.getX(), second.getY()));
    vertices.push_back(second);
    vertices.push_back(Point(second.getX(), first.getY()));
}

bool Square::isCongruentTo(const Shape& another) const {
    const Square* current = dynamic_cast<const Square*>(&another);
    if (current == nullptr) {
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
            
            long double value = Polygon::getPropotionalCoefficient(sidesOfFirstPolygon, sidesOfSecondPolygon);
            if (Point::isEqual(value, 1)) {
                 return true;
            }
        }
        Polygon::shiftPolygon(sidesOfSecondPolygon);
    }
    return false;
}

bool Square::isSimilarTo(const Shape& another) const {
    const Square* current = dynamic_cast<const Square*>(&another);
    if (current == nullptr) {
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
        if (Polygon::isPropotional(sidesOfFirstPolygon, sidesOfSecondPolygon)) {
            return true;
        }
        Polygon::shiftPolygon(sidesOfSecondPolygon);
    }
    return false;
}

bool Square::operator == (const Shape& another) const {
    const Square* current = dynamic_cast<const Square*>(&another);
    if (current == nullptr) {
        return false;
    }
    for (size_t i = 0; i < 4; i++) {
        if (vertices[i] != current->vertices[i]) {
            return false;
        }
    }
    return true;
}