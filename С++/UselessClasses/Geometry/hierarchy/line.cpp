#include "line.h"


const long double Line::PI = 3.1415926;
const long double Line::EPS = 1e-5;

Line::Line(const Line& line) = default;

Line::~Line() = default;

Line::Line(const Point& start, const Point& finish) {
    long double x1 = start.getX();
    long double y1 = start.getY();
    long double x2 = finish.getX();
    long double y2 = finish.getY();
    
    a = y1 - y2;
    b = x2 - x1;
    c = x1 * y2 - x2 * y1;
}

Line::Line(const long double& a, const long double& b, const long double& c) {
    this->a = a;
    this->b = b;
    this->c = c;
}

long double Line::getA() const {
    return a;
}

long double Line::getB() const {
    return b;
}

long double Line::getC() const {
    return c;
}

bool operator == (const Line& left, const Line& right) {
    long double deltaA = abs(left.getA() - right.getA());
    long double deltaB = abs(left.getB() - right.getB());
    long double deltaC = abs(left.getC() - right.getC());
    return deltaA < Line::EPS && deltaB < Line::EPS && deltaC < Line::EPS;
}

bool operator != (const Line& left, const Line& right) {
    return !(left == right);
}