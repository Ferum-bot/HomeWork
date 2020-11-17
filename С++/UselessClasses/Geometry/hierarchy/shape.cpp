#include "shape.h"

virtual Shape::Shape() = 0;

virtual Shape::Shape(const Shape& another) = 0;

virtual Shape::~Shape() = 0;

virtual long double Shape::perimeter() const = 0;
virtual long double Shape::area() const = 0;

virtual bool Shape::isCongruentTo(const Shape& another) const = 0;
virtual bool Shape::isSimilarTo(const Shape& another) const = 0;

virtual bool Shape::containsPoint(const Point& point) const = 0;

virtual void Shape::rotate(const Point& center, const long double& angle) = 0;
virtual void Shape::reflex(const Point& center) = 0;
virtual void Shape::reflex(const Line& axis) = 0;
virtual void Shape::scale(const Point& center, long double& coefficient) = 0;

virtual Shape::bool operator == (const Shape& another) const;
virtual Shape::bool operator != (const Shape& another) const;