#include "segment.h"

const long double Segment::EPS = 1e-5;

Segment::Segment(const Segment& segment) = default;

Segment::~Segment() = default;

Segment::Segment(const Point& first, const Point& second) {
    this->first = first;
    this->second = second;
}

Point Segment::getFirstPoint() const {
    return first;
}

Point Segment::getSecondPoint() const {
    return second;
}

bool operator == (const Segment& left, const Segment& right) {
    bool result = left.first == right.first && left.second == right.second;
    result |= left.first == right.second && left.second == right.first;
    return result;
}

bool operator != (const Segment& left, const Segment& right) {
    return !(left == right);
}