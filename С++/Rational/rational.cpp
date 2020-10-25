#include "rational.h"

Rational::~Rational() = default;

Rational::Rational(const Rational& current) = default;

int Rational::gcd(long long a, long long b) {
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
    if (static_cast<long long>(numerator) * static_cast<long long>(denominator) >= (long long)0) {
        this->numerator = Rational::abs(numerator);
        this->denominator = Rational::abs(denominator);
    }
    else {
        this->numerator = -Rational::abs(numerator);
        this->denominator = Rational::abs(denominator);
    }
    int gcd = Rational::gcd(Rational::abs(this->numerator), this->denominator);
    this->numerator = this->numerator / gcd;
    this->denominator = this->denominator / gcd;
}

int Rational::Numerator() const {
    return numerator;
}

int Rational::Denominator() const {
    return denominator;
}

bool operator == (const Rational& left, const Rational& right) {
    return left.numerator == right.numerator && left.denominator == right.denominator;
}

bool operator != (const Rational& left, const Rational& right) {
    return !(left == right);
}

bool operator < (const Rational& left, const Rational& right) {
    Rational result = left - right;
    if (result.numerator < 0) {
        return true;
    }
    return false;
}

bool operator <= (const Rational& left, const Rational& right) {
    return left < right || left == right;
}

bool operator > (const Rational& left, const Rational& right) {
    return !(left <= right);
}

bool operator >= (const Rational& left, const Rational& right) {
    return !(left < right);
}

Rational operator - (const Rational& left, const Rational& right) {
    long long numeratorValue = static_cast<long long>(left.numerator) * static_cast<long long>(right.denominator);
    numeratorValue -= static_cast<long long>(right.numerator) * static_cast<long long>(left.denominator);
    long long denominatorValue = static_cast<long long>(left.denominator) * static_cast<long long>(right.denominator);
    long long gcd = Rational::gcd(Rational::abs(numeratorValue), denominatorValue);
    numeratorValue /= gcd;
    denominatorValue /= gcd;
    return Rational(numeratorValue, denominatorValue);
}

Rational operator + (const Rational& left, const Rational& right) {
    long long numeratorValue = static_cast<long long>(left.numerator) * static_cast<long long>(right.denominator);
    numeratorValue += static_cast<long long>(right.numerator) * static_cast<long long>(left.denominator);
    long long denominatorValue = static_cast<long long>(left.denominator) * static_cast<long long>(right.denominator);
    long long gcd = Rational::gcd(Rational::abs(numeratorValue), denominatorValue);
    numeratorValue /= gcd;
    denominatorValue /= gcd;
    return Rational(numeratorValue, denominatorValue);
}