#include "list.h"
#include <algorithm>
#include <iostream>
#include <ctime>
#include <cmath>
#include <vector>
#include <random>

std::mt19937 rnd(time(0));

const int sz = 40;
const int64_t MAX_VAL = 100000;

std::vector<int64_t> mas(sz);

void build(task::list& curr) {

}

void test1() {
    task::list l1, l2;
    for (int i = 0; i < sz; i++) {
        mas[i] = rnd() % MAX_VAL;
    }
    for (int i = 0; i < sz; i++) {
        l1.push_back(mas[i]);
    }
    sort(mas.begin(), mas.end());
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
    task::list l1, l2;
    l1.push_back(1);
    l1.push_back(2);
    l1.push_back(3);
   //std::cout << l1.back() << ' ' << l1.front() << std::endl;
    l1.push_front(0);
    l1.pop_back();
    //std::cout << l1.back() << ' ' << l1.front() << std::endl;
    //std::cout << l1.size() << std::endl;
    l2 = l1;
    l1.clear();
    l2.front() = -100;
    //std::cout << l2.back() << ' ' << l2.front() << std::endl;
    l2.push_front(9);
    l2.sort();
    std::cout << l2.size() << std::endl;
    int n = static_cast<int>(l2.size());
    for (int i = 0; i < n; i++) {
        std::cout << l2.front() << ' ';
        l2.pop_front();
    }
}

void test3() {
    task::list l1;
    for (int i = 0; i < sz; i++) {
        mas[i] = (i + 1) % 10;
        l1.push_back(mas[i]);
    }
    sort(mas.begin(), mas.end());
    l1.sort();
    l1.unique();
    for (auto& el : mas) {
        std::cout << el << ' ';
    }
    std::cout << std::endl;
    int n = l1.size();
    for (int i = 0; i < n; i++) {
        std::cout << l1.front() << ' ';
        l1.pop_front();
    }
}

signed main() {
    
    //test1();
    //test2();
    //test3();

    return 0;
}