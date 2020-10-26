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

        bool operator < (const Date& right);
        bool operator <= (const Date& right);
        bool operator > (const Date& right);
        bool operator >= (const Date& right);
        bool operator == (const Date& right);
        bool operator != (const Date& right);
    
    public:

        static int convertToInt(const std::string& value);

    };

public:

    Datebase();

    Datebase(const Datebase& datebase);

    ~Datebase();

    void AddEvent(const Date& date, const std::string& event);
    bool DeleteEvent(const Date& date, const std::string& event);
    int DeleteDate(const Date& date);

    std::vector<std::string> Find(const Date& date) const;
    void Print() const;

    

private:

    std::map<Date, std::vector<std::string>> base;

};

