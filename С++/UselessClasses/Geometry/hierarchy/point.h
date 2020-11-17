#pragma once

#include <math.h>

class Point {
private:

    static const long double PI;
    static const long double EPS;

    long double x;
    long double y;

public:


    Point(const long double& x, const long double& y);

    Point(const Point& point);

    ~Point();

    long double getX() const;
    long double getY() const;

    friend bool operator == (const Point& left, const Point& right);
    friend bool operator != (const Point& left, const Point& right);

};

