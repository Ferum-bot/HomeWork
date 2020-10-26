#include "datebase.h"

Date::Date() {
    year = "0";
    month = "0";
    day = "0";
}

Date::Date(const int& year, const int& month, const int& day) {
    this->year = std::to_string(year);
    this->month = std::to_string(month);
    this->day = std::to_string(day);
}

Date::Date(const std::string& year, const std::string& month, const std::string& day) {
    this->year = year;
    this->month = month;
    this->day = day;
}

Date::Date(const Date& date) = default;

Date::~Date() = default;

void Date::setYear(const std::string& year) {
    if (year.size() == 0) {
        return;
    }
    this->year = year;
}

void Date::setMonth(const std::string& month) {
    if (month.size() == 0) {
        return;
    }
    this->month = month;
}

void Date::setDay(const std::string& day) {
    if (day.size() == 0) {
        return;
    }
    this->day = day;
}

void Date::setYear(const int& year) {
    this->year = std::to_string(year);
}

void Date::setMonth(const int& month) {
     this->month = std::to_string(month);
}

void Date::setDay(const int& day) {
     this->day = std::to_string(day);
}


std::string Date::getYearString() const {
    return year;
}

std::string Date::getMonthString() const {
    return month;
}

std::string Date::getDayString() const {
    return day;
}

int Date::getYearInt() const {
    return Date::convertToInt(year);
}

int Date::getMonthInt() const {
    return Date::convertToInt(month);
}
int Date::getDayInt() const {
    return Date::convertToInt(day);
}

int Date::convertToInt(const std::string& value) {
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

bool operator < (const Date& left, const Date& right) {
    if (left.year == right.year) {
        if (left.month == right.month) {
            return left.day < right.day;
        }
        return left.month < right.month;
    }
    return left.year < right.year;
}

bool operator == (const Date& left, const Date& right) {
    return left.year == right.year && left.month == right.month && left.day == right.day;
}

bool operator != (const Date& left, const Date& right) {
    return !(left == right);
}

bool operator <= (const Date& left, const Date& right) {
    return left < right || (left == right);
}

bool operator > (const Date& left, const Date& right) {
    return !(left <= right);
}

bool operator >= (const Date& left, const Date& right) {
    return !(left < right);
}

Datebase::Datebase() = default;

Datebase::Datebase(const Datebase& datebase) = default;

Datebase::~Datebase() = default;

void Datebase::AddEvent(const Date& date, const std::string& event) {
    if (base.count(date)) {
        std::set<std::string>& current = base[date];
        current.insert(event);
    }
    else {
        std::set<std::string> current;
        current.insert(event);
        base[date] = current;
    }
}

bool Datebase::DeleteEvent(const Date& date, const std::string& event) {
    if (base.count(date)) {
        std::set<std::string>& current = base[date];
        auto it = current.lower_bound(event);
        if (it == current.end()) {
            return false;
        }
        if (*it != event) {
            return false;
        }
        current.erase(it);
        return true;
    }
    else {
        return false;
    }
}

int Datebase::DeleteDate(const Date& date) {
    if (base.count(date)) {
        int result = base[date].size();
        base[date].clear();
        base.erase(date);
        return result;
    }
    else {
        return 0;
    }
}

std::set<std::string> Datebase::Find(const Date& date) const {
    std::set<std::string> result;
    if (base.count(date)) {
        return base.at(date);
    }
    else {
        return result;
    }
}

void Datebase::Print() const {
    for (const auto& el : base) {
        const Date& date = el.first;
        const std::set<std::string>& events = el.second;
        for (const auto& event : events) {
            std::cout << date << ' ' << event << std::endl;
        }
    }
}

std::ostream& operator << (std::ostream& out, const Date& date) {
    
}
