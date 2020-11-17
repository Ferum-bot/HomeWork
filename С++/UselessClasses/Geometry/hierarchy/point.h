#pragma once

class Point {
private:

    const static long double PI = 3.1415926;
    const static long double EPS = 1e-5;

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

