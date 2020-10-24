#include "rational.h"

Rational::~Rational() = default;

Rational::Rational(const Rational& current) = default;

int Rational::gcd(int a, int b) {
    if (a == 0) {
        return b;
    }
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}

int Rational::abs(const int& value) {
    return value >= 0 ? value : -value;
}

Rational::Rational() {
    numerator = 0;
    denominator = 1;
}

Rational::Rational(const int& numerator, const int& denominator) {
    if (numerator == 0) {
        this->numerator = numerator;
        this->denominator = 1;
        return;
    }
    if (static_cast<long long>(numerator) * static_cast<long long>(denominator) >= 0) {
        this->numerator = Rational::abs(numerator);
        this->denominator = Rational::abs(denominator);
    }
    else {
        this->numerator = -Rational::abs(numerator);
        this->denominator = Rational::abs(denominator);
    }
    int gcd = Rational::gcd(this->numerator, this->denominator);
    this->numerator = this->numerator / gcd;
    this->denominator = this->denominator / gcd;
}

int Rational::Numerator() const {
    return numerator;
}

int Rational::Denominator() const {
    return denominator;
}