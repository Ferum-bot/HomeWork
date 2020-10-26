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
    while(current.size() != 0) {
        istringstream input(current);
        string action;
        input >> action;
        Date date;
        input >> date;
        
        getline(cin, current);
    }
    return 0;
}