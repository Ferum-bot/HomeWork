#include "circle.h"

Circle::Circle(const Circle& circle) = default;

Circle::~Circle() = default;

Circle::Circle(const Point& center, const long double& radius) {
    this->center = center;
    this->radius = radius;
}

long double Circle::getRadius() const {
    return radius;
}

Point Circle::getFirstFocus() const {
    return center;
}

Point Circle::getSecondFocus() const {
    return center;
}

std::pair<Point, Point> Circle::focuses() const {
    return {center, center};
}

Point Circle::getCenter() const {
    return center;
}