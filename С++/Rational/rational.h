#include <iostream>

class Rational {
public:

    friend bool operator == (const Rational& left, const Rational& right);
    friend bool operator < (const Rational& left, const Rational& right);
    friend bool operator <= (const Rational& left, const Rational& right);
    friend bool operator > (const Rational& left, const Rational& right);
    friend bool operator >= (const Rational& left, const Rational& right);
    friend bool operator != (const Rational& left, const Rational& right);

    friend Rational operator + (const Rational& left, const Rational& right);
    friend Rational operator - (const Rational& left, const Rational& right);

public:

    Rational();
    Rational(const int& numerator, const int& denominator);

    Rational(const Rational& current);

    ~Rational();

    int Numerator() const;
    int Denominator() const;

private:

    int numerator;
    int denominator;

    static int gcd(long long a, long long b);
    static int abs(const int& value);

};