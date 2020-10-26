#include <iostream>
#include <algorithm>
#include <set>
#include <map>
#include <vector>
#include <string>
#include <cmath>
#include <sstream>

#include "datebase.h"

using namespace std;

signed main() {
    Datebase db;
    string current;
    getline(cin, current);
    while (current.size() != 0) {
        istringstream input(current);
        string action;
        string event;
        input >> action;
        Date date;
        try {
            input >> date;
            input >> event;
        }
        catch(invalid_argument& ex) {
            cout << ex.what() << endl;
            getline(cin, current);
            continue;
        }
        try {
            db.DoAction(action, date, event);
        }
        catch (invalid_argument& ex) {
            cout << ex.what() << endl;
        }
        getline(cin, current);
    }   
    return 0;
}