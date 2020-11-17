#pragma once

#include "point.h"
#include "line.h"

class Shape {
private:

    static const long double PI = 3.1415926;
    static const long double EPS = 1e-5;

public:

    virtual Shape();

    virtual Shape(const Shape& another);

    virtual ~Shape();

    virtual long double perimeter() const;
    virtual long double area() const;

    virtual bool isCongruentTo(const Shape& another) const;
    virtual bool isSimilarTo(const Shape& another) const;

    virtual bool containsPoint(const Point& point) const;

    virtual void rotate(const Point& center, const long double& angle);
    virtual void reflex(const Point& center);
    virtual void reflex(const Line& axis);
    virtual void scale(const Point& center, long double& coefficient);

    virtual bool operator == (const Shape& another) const;
    virtual bool operator != (const Shape& another) const;

};