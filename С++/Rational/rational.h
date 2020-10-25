#include <iostream>
#include <string>

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

    friend Rational operator * (const Rational& left, const Rational& right);
    friend Rational operator / (const Rational& left, const Rational& right);

    friend std::ostream& operator << (std::ostream& out, const Rational& value);
    friend std::istream& operator >> (std::istream& in, Rational& value);

public:

    Rational();
    Rational(const int& numerator, const int& denominator);

    Rational(const Rational& current);

    ~Rational();

    int Numerator() const;
    int Denominator() const;

private:

    friend void checkIsEqualAndAssign(const std::string& currentStream, Rational& value);

    int numerator;
    int denominator;

    static int gcd(long long a, long long b);
    static int abs(const int& value);

    void swap();
};