﻿#include "biginteger.h"
#include <algorithm>
#include <fstream>
#include <cmath>
#include <random>
#include <set>
#include <map>
#include <ctime>

const int sz = 10000; // start size of array
const int64_t MAX_VAL = 10000; // max val of elements
int64_t mas[sz];
std::mt19937 rnd(time(0));
/*
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
				std::cout << "!Error! " << mas[i] << ' ' << mas[j] << "||||" << val1 << ' ' << val2 << ' ';
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
	int kol = 1000; // number of tests
	for (int num = 0; num < kol; num++) {
		std::cout << "Test number: " << num + 1 << ' ' << std::endl;
		int i = rnd() % sz;
		int j = rnd() % sz;
		int h = rnd() % sz;
		BigInteger a = mas[i];
		BigInteger b = mas[j];
		BigInteger c = mas[h];
		std::cout << a << ' ' << b << ' ' << c << std::endl;
		std::vector<int64_t> res1(5, 0);
		std::vector<BigInteger> res2(5, 0);
		res1 = { ++mas[i], mas[i] - ++mas[j], --mas[i], --mas[i] + ++mas[h], mas[i]++ - ++mas[j] + mas[h]-- };
		res2 = { ++a, a - ++b, --a, --a + ++c, a++ - ++b + c-- };
		for (int q = 0; q < 5; q++) {
			if (to_string(res2[q]) == std::to_string(res1[q])) {
				std::cout << "OK ";
			}
			else {
				std::cout << "!Error! " << res1[q] << ' ' << res2[q] << ' ' << "| ";
			}
		}
		std::cout << std::endl;
	}
}

void test4() {
	int kol = 100; // number of tests
	for (int num = 0; num < kol; num++) {
		std::cout << "Test number " << num + 1 << " :" << std::endl;
		int i = rnd() % sz;
		int j = rnd() % sz;
		int k = rnd() % sz;
		BigInteger a = mas[i];
		BigInteger b = mas[j];
		BigInteger c = mas[k];
		int ch1 = mas[i];
		int ch2 = mas[j];
		int ch3 = mas[k];
		ch1 += ch2;
		a += b;
		ch2 -= ch3;
		b -= c;
		ch3 += ++ch1;
		c += ++a;
		ch1 -= ++ch2;
		a -= ++b;
		++ch2 += --ch3;
		++b += --c;
		std::vector<int64_t> res1 = { ch1, ch2, ch3 };
		std::vector<BigInteger> res2 = { a, b, c };
		for (int h = 0; h < 3; h++) {
			if (to_string(res2[h]) == std::to_string(res1[h])) {
				std::cout << "OK" << ' ';
			}
			else {
				std::cout << "!Error!" << res1[h] << ' ' << res2[h] << "|| ";
			}
		}
		std::cout << std::endl;
	}
}

void test5() {
	int kol = 100; // Number of tests
	for (int num = 0; num < kol; num++) {
		std::cout << "Test number " << num + 1 << " :" << std::endl;
		int i = rnd() % sz;
		int j = rnd() % sz;
		int k = rnd() % sz;
		BigInteger a = mas[i];
		BigInteger b = mas[j];
		BigInteger c = mas[k];
		int64_t val1 = mas[i], val2 = mas[j], val3 = mas[k];
		std::vector<int64_t> res1 = { val1 * val2, val1 * val2 * val3, val1 + val2 * val3 - val3, ++val1 * val3 };
		std::vector<BigInteger> res2 = { a * b, a * b * c, a + b * c - c, ++a * c };
		for (int h = 0; h < (int)res1.size(); h++) {
			if (std::to_string(res1[h]) == to_string(res2[h])) {
				std::cout << "OK" << ' ';
			}
			else {
				std::cout << "!Error! " << res1[h] << ' ' << res2[h] << "| ";
			}
		}
		std::cout << std::endl;
	}
}

void test6() {
	int kol = 100; // numebr of tests
	for (int num = 0; num < kol; num++) {
		std::cout << "Test number " << num + 1 << " :" << std::endl;
		int i = rnd() % sz;
		int j = rnd() % sz;
		int h = rnd() % sz;
		int64_t ch1 = mas[i];
		int64_t ch2 = mas[j];
		int64_t ch3 = mas[h];
		BigInteger a = mas[i];
		BigInteger b = mas[j];
		BigInteger c = mas[h];
		std::vector<int64_t> res1 = { ch1 / ch2 * ch3 + ch1, ch1 / ch2, ch2 / ch1 - ch3, ch1 * ch2 / ch3 + --ch1, ch1++ + ch3 - ch2 / ch1 };
		std::vector<BigInteger> res2 = {a / b * c + a, a / b, b / a - c, a * b / c + --a, a++ + c - (b / a)};
		for (int k = 0; k < (int)res1.size(); k++) {
			if (std::to_string(res1[k]) == to_string(res2[k])) {
				std::cout << "OK "; 
			}
			else {
				std::cout << "!Error! " << a << ' ' << b << " " << c << ' ' << res1[k] << ' ' << res2[k] << ' ';
			}
		}
		std::cout << std::endl;
	}
}

void test7() {
	int kol = 40; // number of tests
	for (int num = 1; num <= kol; num++) {
		std::cout << "Test number " << num << " :" << std::endl;
		int i1 = rnd() % sz;
		int i2 = rnd() % sz;
		int i3 = rnd() % sz;
		int i4 = rnd() % sz;
		BigInteger a = mas[i1];
		BigInteger b = mas[i2];
		BigInteger c = mas[i3];
		BigInteger d = mas[i4];
		int64_t ch1 = mas[i1];
		int64_t ch2 = mas[i2];
		int64_t ch3 = mas[i3];
		int64_t ch4 = mas[i4];
		ch1 *= ch2++;
		ch1 %= ch3 * ch1 / ch4 + ch2;
		ch1 /= ch1 * ch1 % ch3 - ch2++;
		ch4 -= ch1 % ch2 + ch3 / ch4;
		ch2 %= ch2 * ch3 - ch3 / ch1;
		ch3 /= ch1 % ch2 % ch4 * ch1;
		a *= b++;
		a %= c * a / d + b;
		a /= a * a % c - b++;
		d -= a % b + c / d;
		b %= b * c - c / a;
		c /= a % b % d * a;
		std::vector<int64_t> res1 = { ch1, ch2, ch3, ch4 };
		std::vector<BigInteger> res2 = { a, b, c, d };
		for (int j = 0; j < 4; j++) {
			if (std::to_string(res1[j]) == to_string(res2[j])) {
				std::cout << "OK ";
			}
			else {
				std::cout << "!Error! " << a << ' ' << b << ' ' << c << ' ' << d << ' ';
			}
		}
		std::cout << "\n";
	}
}

void test8() {

}
*/

using std::cin;
using std::cout;
using std::endl;

signed main() {

	//for (int i = 0; i < sz; i++) {
	//	mas[i] = rnd() % (MAX_VAL);
	//}
	//test1(); // testing: > < >= <= == !=
	//test2(); // testing: + -
	//test3(); // testing: + - and different options of ++ --
	//test4(); // testing += and some other features
	//test5(); // testing * and + -
	//test6(); // testing / and *
	//test7(); // testing % and *= /=
	//test8() // test all features

	BigInteger a = 3;
	BigInteger b = 2;
	cout << a + b << endl;
	return 0;
}

/*
* 5532 950 739 12641 6242
*/