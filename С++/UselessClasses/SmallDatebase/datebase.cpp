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
    out << std::setfill('0');
    out << std::setw(4);
    out << date.year;
    out << '-';
    out << std::setw(2);
    out << date.month;
    out << '-';
    out << std::setw(2);
    out << date.day;
    return out;
}


std::istream& operator >> (std::istream& in, Date& date) {
    std::string current;
    in >> current;
    if (current.size() == 0) {
        return in;
    }
    date = Date::getAllMembersFromString(current);
    date.checkForCorrect();
    return in;
}

Date Date::getAllMembersFromString(const std::string& current) {
    for (size_t i = 0; i < current.size(); i++) {
        if (current[i] == '-' || current[i] == '+' || Date::isDigit(current[i])) {
            continue;
        }
        std::string excep = "Wrong date format: " + current;
        throw std::invalid_argument(excep);
    }
    size_t posOfFirstSeparator = 0;
    size_t posOfSecondSeparator = 0;
    int numberOfSeparators = 0;
    for (size_t i = 0; i < current.size(); i++) {
        if (current[i] == '-' && i != 0) {
            posOfFirstSeparator = i;
            numberOfSeparators++;
            break;
        }
    }
    if (numberOfSeparators == 0) {
        std::string excep = "Wrong date format: " + current;
        throw std::invalid_argument(excep);
    }
    for (size_t i = posOfFirstSeparator + 1; i < current.size(); i++) {
        if (current[i] != '-') {
            continue;
        }
        if (current[i] == '-' && i == current.size() - 1) {
            std::string excep = "Wrong date format: " + current;
            throw std::invalid_argument(excep);
        }
        if (current[i] == '-' && !Date::isDigit(current[i + 1])) {
            posOfSecondSeparator = i;
            numberOfSeparators++;
            break;
        }
        if (current[i] == '-' && Date::isDigit(current[i + 1]) && i - 1 == posOfFirstSeparator) {
            continue;
        }
        posOfSecondSeparator = i;
        numberOfSeparators++;
        break;
    }
    if (numberOfSeparators == 1) {
        std::string excep = "Wrong date format: " + current;
        throw std::invalid_argument(excep);
    }
    Date result;
    result.year = Date::getValueFrom(current, 0, posOfFirstSeparator);
    result.month = Date::getValueFrom(current, posOfFirstSeparator + 1, posOfSecondSeparator);
    result.day = Date::getValueFrom(current, posOfSecondSeparator + 1, current.size());
    return result;
}

std::string Date::getValueFrom(const std::string& current, const size_t& left, const size_t& right) {
    int result = 0;
    int numberOfPlus = 0;
    int numberOfMinus = 0;
    for (size_t i = left; i < right; i++) {
        if (current[i] == '+') {
            numberOfPlus++;
            continue;
        }
        if (current[i] == '-') {
            numberOfMinus++;
            continue;
        }
        result *= 10;
        result += current[i] - '0';
    }
    if (numberOfPlus > 1) {
        std::string excep = "Wrong date format: " + current;
        throw std::invalid_argument(excep);
    }
    if (numberOfMinus > 1) {
        std::string excep = "Wrong date format: " + current;
        throw std::invalid_argument(excep);
    }
    if (numberOfMinus == 1) {
        result *= -1;
    } 
    return std::to_string(result);
}

bool Date::isDigit(const char& digit) {
    return digit >= '0' && digit <= '9';
}

void Date::checkForCorrect() const {
    if (Date::convertToInt(month) < 1 || Date::convertToInt(month) > 12) {
        std::string excep = "Month value is invalid: " + month;
        throw std::invalid_argument(excep);
    }
    if (Date::convertToInt(day) < 1 || Date::convertToInt(day) > 31) {
        std::string excep = "Day value is invalid: " + day;
        throw std::invalid_argument(excep);
    }
}

void Datebase::DoAction(const std::string& action, const Date& date, const std::string& event) {
    Datebase::CheckTheAction(action);
    if (action == "Add") {
        AddEvent(date, event);
        return;
    }
    if (action == "Find") {
        std::set<std::string> result = Find(date);
        for (const auto& el : result) {
            std::cout << el << std::endl;
        }
        return;
    }
    if (action == "Print") {
        for (const auto& el : base) {
            const Date& date = el.first;
            for (const auto& el : el.second) {
                std::cout << date << ' ' << el << std::endl;
            }
        }
        return;
    }
    if (action == "Del") {
        if (event.size() == 0) {
            std::cout << "Deleted " << DeleteDate(date) << " events" << std::endl;
        }
        else {
            if (DeleteEvent(date, event)) {
                std::cout << "Deleted successfully" << std::endl;
            }
            else {
                std::cout << "Event not found" << std::endl;
            }
        }
        return;
    }
}

void Datebase::CheckTheAction(const std::string& action) {
    if (action == "Add") {
        return;
    }
    if (action == "Del") {
        return;
    }
    if (action == "Find") {
        return;
    }
    if (action == "Print") {
        return;
    }
    std::string excep = "Unknown command: " + action;
    throw std::invalid_argument(excep);
}