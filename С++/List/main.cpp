#include "list.h"
#include <algorithm>
#include <iostream>
#include <ctime>
#include <cmath>
#include <vector>
#include <random>

std::mt19937 rnd(time(0));

const int sz = 10;
const int64_t MAX_VAL = 100;

std::vector<int64_t> mas(sz);

void build(task::list& curr) {

}

void test1() {
    task::list cur(8, 100);
    std::cout << cur.size() << std::endl;
    std::cout << cur.front() << ' ' << cur.back() << std::endl;
    return;
}

void test2() {

}

void test3() {

}

signed main() {
    
    test1();
    //test2();
    //test3();

    return 0;
}