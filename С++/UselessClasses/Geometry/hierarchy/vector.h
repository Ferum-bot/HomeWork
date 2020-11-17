#pragma once

#include "point.h"

#include <utility>

class Vector final {
private:

    static const long double PI;
    static const long double EPS;

    long double x;
    long double y;

public:

    Vector(const long double& x, const long double& y);
    Vector(const Point& start, const Point& finish);
    Vector(const Point& point);

    Vector(const Vector& vector);

    ~Vector();

    long double getX() const;
    long double getY() const;

    long double getLength() const;

    friend bool operator == (const Vector& left, const Vector& right);
    friend bool operator != (const Vector& left, const Vector& right);

public:

    static long double dotProduct(const Vector& first, const Vector& second);
    static long double crossProduct(const Vector& first, const Vector& second);

    static std::pair<Vector, Vector> getTwoVectorsFromPoints(const Point& first, 
                                        const Point& second, const Point& third);

};