#include <iostream>
#include <map>
#include <set>
#include <string>
#include <iomanip>

class Date {
private:

    std::string year;
    std::string month;
    std::string day;

public:

    Date();
    Date(const int& year, const int& month, const int& day);
    Date(const std::string& year, const std::string& month, const std::string& day);

    Date(const Date& date);

    ~Date();

    void setYear(const std::string& year);
    void setMonth(const std::string& month);
    void setDay(const std::string& day);

    void setYear(const int& year);
    void setMonth(const int& month);
    void setDay(const int& day);

    std::string getYearString() const;
    std::string getMonthString() const;
    std::string getDayString() const;

    int getYearInt() const;
    int getMonthInt() const;
    int getDayInt() const;

public:

    friend bool operator < (const Date& left, const Date& right);
    friend bool operator <= (const Date& left, const Date& right);
    friend bool operator > (const Date& left, const Date& right);
    friend bool operator >= (const Date& left, const Date& right);
    friend bool operator == (const Date& left, const Date& right);
    friend bool operator != (const Date& left, const Date& right);

    friend std::ostream& operator << (std::ostream& out, const Date& date);
    friend std::istream& operator >> (std::istream& in, Date& date);

private:

    static int convertToInt(const std::string& value);
    static bool isDigit(const char& digit);
    static Date getAllMembersFromString(const std::string& current);
    static int getValueFrom(const std::string& current, const size_t& left, const size_t& right);

    void checkForCorrect() const;

};

class Datebase {
public:

    Datebase();

    Datebase(const Datebase& datebase);

    ~Datebase();

    void AddEvent(const Date& date, const std::string& event);
    bool DeleteEvent(const Date& date, const std::string& event);
    int DeleteDate(const Date& date);

    std::set<std::string> Find(const Date& date) const;
    void Print() const;

    void DoAction(const std::string& action, const Date& date, const std::string& event = "");

private:

    std::map<Date, std::set<std::string>> base;

    static void CheckTheAction(const std::string& action);

};

