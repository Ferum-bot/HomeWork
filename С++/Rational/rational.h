#include <iostream>

class Rational {
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

    static int gcd(int a, int b);
    static int abs(const int& value);

};