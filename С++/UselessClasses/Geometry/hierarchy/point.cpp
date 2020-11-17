#pragma once

#include "point.h"

Point::Point(const Point& point) = default;

Point::~Point() = default;

Point::Point(const long double& x, const long double& y) {
    this->x = x;
    this->y = y;
}

long double Point::getX() const {
    return x;
}

long double Point::getY() const {
    return y;
}

bool operator == (const Point& left, const Point& right) {
    long double deltaX = abs(left.getX - right.getX);
    long double deltaY = abs(left.getY - right.getY);
    return deltaX < Point::EPS && deltaY < Point::EPS;
}

bool operator != (const Point& left, const Point& right) {
    return !(left == right);
}