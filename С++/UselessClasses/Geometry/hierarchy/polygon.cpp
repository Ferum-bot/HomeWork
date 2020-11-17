#include "polygon.h"

Polygon::Polygon(const Polygon& polygon) = default;

Polygon::~Polygon() = default;

Polygon::Polygon(const std::vector<Point>& vertices) {
    if (vertices.size() < 3) {
        throw std::invalid_argument("Invalid number of vertices");
    }
    this->vertices = vertices;
}

size_t Polygon::verticesCount() const {
    return vertices.size();
}

std::vector<Point> Polygon::getVertices() const {
    return vertices;
}

bool Polygon::isConvex() const {
    size_t size = vertices.size();
    int type = -1;
    for (size_t i = 0; i < size; i++) {
        std::pair<Vector, Vector> resultVectors = Vector::getTwoVectorsFromPoints(vertices[i], vertices[(i + 1) % size], vertices[(i + 2) % size]);
        const Vector& v1 = resultVectors.first;
        const Vector& v2 = resultVectors.second;
        const long double resultOfCrossProduct = Vector::crossProduct(v1, v2);
        if (!Polygon::isCorrectValue(type, resultOfCrossProduct)) {
            return false;
        }
    }
    if (type == -1) {
        return false;
    }
    return true;
}

bool Polygon::isCorrectValue(int& type, const long double& value) {
    if (Polygon::isEqualToZero(value)) {
        return true;
    }
    if (type == -1) {
        type = Polygon::isAboveZero(value);
    }
    else {
        if (type != Polygon::isAboveZero(value)) {
            return false;
        }
    }
    return true;
}

bool Polygon::isEqualToZero(const long double& value) {
    return abs(value) <= Polygon::EPS;
}

bool Polygon::isAboveZero(const long double& value) {
    return value > Polygon::EPS;
}

bool Polygon::isLessZero(const long double& value) {
    return value < -Polygon::EPS;
}