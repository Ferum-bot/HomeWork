#include <string>
#include <vector>
#include <iostream>

class BigInteger {
private:
	std::vector<char> number;

	std::vector<char> make_number(const int& number) {
		std::vector<char> result;
		if (number == 0) {
			result.push_back('+');
			result.push_back('0');
			return result;
		}
		int cur_number;
		if (number > 0) {
			result.push_back('+');
			cur_number = number;
		}
		else {
			result.push_back('-');
			cur_number = number * (-1);
		}
		while (cur_number != 0) {
			result.push_back('0' + cur_number % 10);
			cur_number /= 10;
		}
		return result;
	}

	std::vector<char> make_number(const std::string& number) {
		if (number.size() == 0) {
			return make_number(0);
		}
		std::vector<char> result;
		if (number[0] == '-') {
			result.push_back('-');
		}
		else {
			result.push_back('+');
		}
		int n = number.size();
		for (int i = n - 1; i >= 0; i--) {
			if (number[i] == '-' || number[i] == '+') {
				continue;
			}
			result.push_back(number[i]);
		}
		return result;
	}
public:
	BigInteger() {
		this->number = make_number(0);
		return;
	}

	BigInteger(const int& number) {
		this->number = make_number(number);
		return;
	}

	BigInteger& operator = (const int& number) {
		this->number = make_number(number);
		return *this;
	}

	friend std::istream& operator >>(std::istream& cin, BigInteger& number);
	friend std::ostream& operator <<(std::ostream& cout, const BigInteger& number);

	friend std::string to_string(const BigInteger& number);

};

std::string to_string(const BigInteger& number) {
	std::string result = "";
	if (number.number[0] == '-') {
		result = result + number.number[0];
	}
	int n = number.number.size();
	for (int i = n - 1; i > 0; i--) {
		result = result + number.number[i];
	}
	return result;
}

std::istream& operator >>(std::istream& cin, BigInteger& number) {
	std::string cur_number;
	std::cin >> cur_number;
	number.number = number.make_number(cur_number);
	return cin;
}

std::ostream& operator <<(std::ostream& cout, const BigInteger& number) {
	std::cout << to_string(number) << std::endl;
	return cout;
}