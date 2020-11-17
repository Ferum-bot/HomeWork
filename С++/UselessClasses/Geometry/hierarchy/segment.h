#pragma once

#include "point.h"

class Segment final {
private:

    static const long double EPS;

    Point first;
    Point second;

public:

    Segment(const Point& first, const Point& second);

    Segment(const Segment& segment);

    ~Segment();

    Point getFirstPoint() const;
    Point getSecondPoint() const;

    friend bool operator == (const Segment& left, const Segment& right);
    friend bool operator != (const Segment& left, const Segment& right);

};