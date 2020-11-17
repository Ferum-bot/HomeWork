#pragma once

#include <math.h>

class Point final {
private:

    static const long double PI;
    static const long double EPS;
    static const long double INF;

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

public:

    static long double getDist(const Point& first, const Point& second);

};

