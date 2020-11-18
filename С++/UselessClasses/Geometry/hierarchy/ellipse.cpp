#include "ellipse.h"

Ellipse::Ellipse(const Ellipse& ellipse) = default;

Ellipse::~Ellipse() = default;

Ellipse::Ellipse() {}

Ellipse::Ellipse(const Point& first, const Point& second, const long double& sumOfDistance) {
    firstFocus = first;
    secondFocus = second;
    this->sumOfDistance = sumOfDistance;
    
}

Point Ellipse::getFirstFocus() const {
    return firstFocus;
}

Point Ellipse::getSecondFocus() const {
    return secondFocus;
}

std::pair<Point, Point> Ellipse::focuses() const {
    return {firstFocus, secondFocus};
}

