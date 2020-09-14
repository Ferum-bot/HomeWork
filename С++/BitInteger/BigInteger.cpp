#include "biginteger.h"
#include <algorithm>
#include <fstream>
#include <cmath>
#include <random>
#include <set>
#include <map>
#include <ctime>

const int sz = 1000; // start size of array
int64_t mas[sz];
std::mt19937 rnd(time(0));

void test1() {
	int kol = 100; // Number of tests
	for (int num = 0; num < kol; num++) {
		int i = rnd() % sz;
		int j = rnd() % sz;
		BigInteger val1 = mas[i];
		BigInteger val2 = mas[j];
		std::vector<bool> res1(6, 0);
		std::vector<bool> res2(6, 0);
		res1 = { mas[i] == mas[j], mas[i] != mas[j], mas[i] > mas[j], mas[i] >= mas[j], mas[i] < mas[j], mas[i] <= mas[j] };
		res2 = { val1 == val2, val1 != val2, val1 > val2, val1 >= val2, val1 < val2, val1 <= val2 };
		std::cout << "Test number " << num + 1 << " :" << std::endl;
		for (int k = 0; k < 6; k++) {
			if (res1[k] == res2[k]) {
				std::cout << "OK ";
			}
			else {
				std::cout << "!Error! "  << mas[i] << ' ' << mas[j] << "||||" << val1 << ' ' << val2 << ' ';
			}
		}
		std::cout << std::endl << std::endl;
	}
}
void test2() {
	int kol = 150; // Number of tests
	for (int num = 0; num < kol; num++) {
		std::cout << "Test number " << num + 1 << ":" << std::endl;
		int i = rnd() % sz;
		int j = rnd() % sz;
		int h = rnd() % sz;
		int k = rnd() % sz;
		BigInteger val1 = mas[i];
		BigInteger val2 = mas[j];
		BigInteger val3 = mas[h];
		BigInteger val4 = mas[k];
		std::vector<int64_t> res1(6, 0);
		std::vector<BigInteger> res2(6, 0);
		res1 = { mas[i] + mas[j], mas[i] - mas[j], mas[i] + mas[j] + mas[h], mas[i] - mas[j] - mas[h], mas[i] + mas[j] + mas[h] + mas[k], mas[i] - mas[j] + mas[h] - mas[k] };
		res2 = { val1 + val2, val1 - val2, val1 + val2 + val3, val1 - val2 - val3, val1 + val2 + val3 + val4, val1 - val2 + val3 - val4 };
		for (int q = 0; q < 6; q++) {
			if (std::to_string(res1[q]) == to_string(res2[q])) {
				std::cout << "OK ";
			}
			else {
				std::cout << "!Error! " << res1[q] << ' ' << res2[q] << ' ' << val1 << ' ' << val2 << ' ' << val3 << ' ' << val4 << "||";
			}
		}
		std::cout << std::endl;
	}
}

void test3() {

}

signed main() {

	for (int i = 0; i < sz; i++) {
		mas[i] = rnd() % ((int)1e9);
	}
	//test1(); // testing: > < >= <= == !=
	//test2(); // testing: + -
	//test3();
	return 0;
}

/*
-1567003980 -890817537
1 3 8 6 (6)
8 9 6 5 (6)
*/