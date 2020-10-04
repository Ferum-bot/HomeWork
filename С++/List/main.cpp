#include "list.h"
#include <algorithm>
#include <iostream>
#include <ctime>
#include <cmath>
#include <vector>
#include <random>

std::mt19937 rnd(time(0));

const int sz = 30;
const int64_t MAX_VAL = 10000;

std::vector<int64_t> mas(sz);

void build(task::list& curr) {

}

void test1() {
    task::list l1, l2;
    for (int i = 0; i < sz; i++) {
        mas[i] = rnd() % MAX_VAL;
    }
    sort(mas.begin(), mas.end());
    for (int i = 0; i < sz; i++) {
        l1.push_back(mas[i]);
    }
    l1.sort();
    for (int i = 0; i < sz; i++) {
        std::cout << mas[i] << ' ';
    }
    std::cout << std::endl;
    for (int i = 0; i < sz; i++) {
        std::cout << l1.front() << ' ';
        l1.pop_front();
    }
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