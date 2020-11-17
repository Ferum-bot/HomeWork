#pragma once

#include "point.h"
#include <math.h>

class Line {
private:

    const static long double PI = 3.1415926;
    const static long double EPS = 1e-5;

    long double a;
    long double b;
    long double c;

public:

    Line(const Point& start, const Point& finish);

    Line(const long double& a, const long double& b, const long double& c);

    Line(const Line& line);

    ~Line();

    long double getA() const;
    long double getB() const;
    long double getC() const;

    friend bool operator == (const Line& left, const Line& right);
    friend bool operator != (const Line& left, const Line& right);

};