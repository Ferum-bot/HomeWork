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
    task::list l1, l2;
    l1.push_back(4);
    l1.push_back(5);
    l1.push_front(3);
    l1.push_front(2);
    l1.push_back(6);
    std::cout << l1.front() << ' ' << l1.back() << std::endl;
    l1.push_back(3);
    l1.remove(3);
    std::cout << l1.size() << std::endl;
    l1.pop_front();
    std::cout << l1.front() << ' ' << l1.back() << std::endl;
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