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

void Rational::swap() {
    std::swap(numerator, denominator);
    if (denominator < 0) {
        numerator = -numerator;
        denominator = -denominator;
    }
    return;
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

Rational operator * (const Rational& left, const Rational& right) {
    Rational result;
    int gcd1 = Rational::gcd(Rational::abs(left.numerator), right.denominator);
    int gcd2 = Rational::gcd(Rational::abs(right.numerator), left.denominator);
    result.numerator = (left.numerator / gcd1) * (right.numerator / gcd2);
    result.denominator = (left.denominator / gcd2) * (right.denominator / gcd1);
    return result;
}

Rational operator / (const Rational& left, const Rational& right) {
    Rational currentRight = right;
    currentRight.swap();
    return left * currentRight;
}

std::ostream& operator << (std::ostream& out, const Rational& value) {
    out << value.Numerator() << '/' << value.Denominator();
    return out;
}

std::istream& operator >> (std::istream& in, Rational& value) {
    std::string currentStream;
    in >> currentStream;
    checkIsEqualAndAssign(currentStream, value);
    return in;
}

void checkIsEqualAndAssign(const std::string& currentStream, Rational& value) {
    if (currentStream.size() == 0) {
        return;
    }
    size_t posOfChar = 0;
    int numberOfChar = 0;
    for (size_t i = 0; i < currentStream.size(); i++) {
        if (currentStream[i] == '/') {
            posOfChar = i;
            numberOfChar++;
            continue;
        }
        if (currentStream[i] == '+' || currentStream[i] == '-') {
            if (i == 0) {
                continue;
            }
            if (numberOfChar == 1 && posOfChar + 1 == i) {
                continue;
            }
            return;
        }
        if (currentStream[i] < '0' || currentStream[i] > '9') {
            return;
        }
    }
    if (numberOfChar != 1) {
        return;
    }
    if (posOfChar == 0 || posOfChar == currentStream.size() - 1) {
        return;
    }
    int numerator = 0, denominator = 0;
    bool sign = true;
    for (size_t i = 0; i < posOfChar; i++) {
        if (currentStream[i] == '+') {
            continue;
        }
        if (currentStream[i] == '-') {
            sign = false;
            continue;
        }
        numerator *= 10;
        numerator += currentStream[i] - '0';
    }
    for (size_t i = posOfChar + 1; i < currentStream.size(); i++) {
        if (currentStream[i] == '+') {
            continue;
        }
        if (currentStream[i] == '-') {
            sign = sign == false;
            continue;
        }
        denominator *= 10;
        denominator += currentStream[i] - '0';
    }
    if (!sign) {
        numerator *= -1;
    }
    value = Rational(numerator, denominator);
}