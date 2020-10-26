#include <iostream>
#include <map>
#include <vector>
#include <string>

class Datebase {
private:

    struct Date {
    public:

        std::string year;
        std::string month;
        std::string day;

    public:

        void setYear();
        void setMonth();
        void setDay();

        string getYearString() const;
        string getMonthString() const;
        string getDayString() const;

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

    };

public:

    void AddEvent(const Date& date, const std::string& event);
    bool DeleteEvent(const Date& date, const std::string& event);
    int DeleteDate(const Date& date);

    vector<string> Find(const Date& date) const;
    void Print() const;

};

