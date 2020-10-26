#include "datebase.h"

Datebase::Date::Date() {
    year = "0";
    month = "0";
    day = "0";
}

Datebase::Date::Date(const int& year, const int& month, const int& day) {
    this->year = std::to_string(year);
    this->month = std::to_string(month);
    this->day = std::to_string(day);
}

Datebase::Date::Date(const std::string& year, const std::string& month, const std::string& day) {
    this->year = year;
    this->month = month;
    this->day = day;
}

Datebase::Date::Date(const Date& date) = default;

Datebase::Date::~Date() = default;

void Datebase::Date::setYear(const std::string& year) {
    if (year.size() == 0) {
        return;
    }
    this->year = year;
}

void Datebase::Date::setMonth(const std::string& month) {
    if (month.size() == 0) {
        return;
    }
    this->month = month;
}

void Datebase::Date::setDay(const std::string& day) {
    if (day.size() == 0) {
        return;
    }
    this->day = day;
}

void Datebase::Date::setYear(const int& year) {
    this->year = std::to_string(year);
}

void Datebase::Date::setMonth(const int& month) {
     this->month = std::to_string(month);
}

void Datebase::Date::setDay(const int& day) {
     this->day = std::to_string(day);
}


std::string Datebase::Date::getYearString() const {
    return year;
}

std::string Datebase::Date::getMonthString() const {
    return month;
}

std::string Datebase::Date::getDayString() const {
    return day;
}

int Datebase::Date::getYearInt() const {
    return Datebase::Date::convertToInt(year);
}

int Datebase::Date::getMonthInt() const {
    return Datebase::Date::convertToInt(month);
}
int Datebase::Date::getDayInt() const {
    return Datebase::Date::convertToInt(day);
}

int Datebase::Date::convertToInt(const std::string& value) {
    int result = 0;
    bool sign = true;
    for (const auto& el : value) {
        if (el == '+') {
            continue;
        }
        if (el == '-') {
            sign = false;
            continue;
        }
        result *= 10;
        result += el - '0';
    }
    return result;
}

bool Datebase::Date::operator < (const Date& right) {
    if (this->year == right.year) {
        if (this->month == right.month) {
            return this->day < right.day;
        }
        return this->month < right.month;
    }
    return this->year < right.year;
}

bool Datebase::Date::operator == (const Date& right) {
    return this->year == right.year && this->month == right.month && this->day == right.day;
}

bool Datebase::Date::operator != (const Date& right) {
    return !((*this) == right);
}

bool Datebase::Date::operator <= (const Date& right) {
    return (*this) < right || (*this == right);
}

bool Datebase::Date::operator > (const Date& right) {
    return !((*this) <= right);
}

bool Datebase::Date::operator >= (const Date& right) {
    return !((*this) < right);
}
