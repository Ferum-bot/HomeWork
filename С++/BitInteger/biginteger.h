#include <string>
#include <vector>
#include <iostream>

class BigInteger {
protected:
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

	friend int get_digit(const BigInteger& number, const int& id);
	friend BigInteger addition(const BigInteger& first_num, const BigInteger& second_num);
	friend void recalc(BigInteger& number, int pos);
	friend BigInteger subtraction(BigInteger& first_num, BigInteger& second_num);

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

	bool operator == (const BigInteger& number) {
		if (this->number.size() != number.number.size()) {
			return false;
		}
		int n = this->number.size();
		for (int i = 0; i < n; i++) {
			if (this->number[i] != number.number[i]) {
				return false;
			}
		}
		return true;
	}

	bool operator != (const BigInteger& number) {
		return !(*this == number);
	}

	bool operator > (const BigInteger& number) {
		if (*this == number) {
			return false;
		}
		bool sign1 = this->number[0] == '+' ? 1 : 0;
		bool sign2 = number.number[0] == '+' ? 1 : 0;
		if (sign1 != sign2) {
			return sign1 > sign2;
		}
		if (this->number.size() > number.number.size()) {
			return sign1 ? true : false;
		}
		if (this->number.size() < number.number.size()) {
			return sign1 ? false : true;
		}
		int n = this->number.size();
		for (int i = n - 1; i > 0; i--) {
			if (this->number[i] == number.number[i]) {
				continue;
			}
			if (sign1) {
				return this->number[i] > number.number[i];
			}
			return this->number[i] < number.number[i];
		}
	}

	bool operator < (const BigInteger& number) {
		if (*this == number) {
			return false;
		}
		BigInteger cur1 = *this;
		BigInteger cur2 = number;
		return cur2 > cur1;
	}

	bool operator >= (const BigInteger& number) {
		return *this > number || *this == number;
	}

	bool operator <= (const BigInteger& number) {
		return *this < number || *this == number;
	}

	/*BigInteger operator + (const BigInteger& number);
	BigInteger operator - (const BigInteger& number);*/

	BigInteger operator +  (const BigInteger& number) {
		bool sign1 = this->number[0] == '+' ? 1 : 0;
		bool sign2 = number.number[0] == '+' ? 1 : 0;
		BigInteger val1 = *this;
		BigInteger val2 = number;
		if (sign1 == sign2) {
			return addition(val1, val2);
		}
		if (sign1 > sign2) {
			val2.number[0] = '+';
			return subtraction(val1, val2);
		}
		val1.number[0] = '+';
		return subtraction(val2, val1);
	}

	BigInteger operator -(BigInteger& number) {
		bool sign1 = this->number[0] == '+' ? 1 : 0;
		bool sign2 = number.number[0] == '+' ? 1 : 0;
		if (sign1 && sign2) {
			return subtraction(*this, number);
		}
		if (sign1 && !sign2) {
			return addition(*this, number);
		}
		if (!sign1 && sign2) {
			BigInteger cur_num2 = number;
			cur_num2.number[0] = '-';
			return addition(*this, cur_num2);
		}
		if (!sign1 && !sign2) {
			return subtraction(number, *this);
		}
	}
	BigInteger operator -() const {
		BigInteger cur_num = 0;
		if (cur_num == *this) {
			return *this;
		}
		cur_num = *this;
		if (this->number[0] == '+') {
			cur_num.number[0] = '-';
		}
		else {
			cur_num.number[0] = '+';
		}
		return cur_num;
	}
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
	std::cout << to_string(number);
	return cout;
}

int get_digit(const BigInteger& number, const int& id) {
	if ((int)number.number.size() <= id) {
		return 0;
	}
	return number.number[id] - '0';
}

BigInteger addition(const BigInteger& first_num, const BigInteger& second_num) {
	BigInteger result;
	if (first_num.number[0] == '-') {
		result.number[0] = '-';
	}
	result.number.pop_back();
	int n = first_num.number.size() >= second_num.number.size() ? first_num.number.size() : second_num.number.size();
	int add = 0;
	for (int i = 1; i < n; i++) {
		int digit1 = get_digit(first_num, i);
		int digit2 = get_digit(second_num, i);
		int sum = digit1 + digit2 + add;
		result.number.push_back('0' + sum % 10);
		add = sum / 10;
	}
	if (add != 0) {
		result.number.push_back('0' + add);
	}
	return result;
}

void recalc(BigInteger& number, int pos) {
	int st = pos;
	pos++;
	while (number.number[pos] == '0') {
		pos++;
	}
	number.number[pos]--;
	pos--;
	while (pos != st) {
		number.number[pos] = '9';
		pos--;
	}
	return;
}

BigInteger subtraction(BigInteger& first_num, BigInteger& second_num) {
	BigInteger result;
	BigInteger num1 = first_num, num2 = second_num;
	if (num1 == num2) {
		return result;
	}
	if (num1 < num2) {
		result = subtraction(num2, num1);
		result.number[0] = '-';
		return result;
	}
	result.number.pop_back();
	int n = num1.number.size();
	for (int i = 1; i < n; i++) {
		int digit1 = get_digit(num1, i);
		int digit2 = get_digit(num2, i);
		if (digit1 >= digit2) {
			result.number.push_back('0' + (digit1 - digit2));
			continue;
		}
		recalc(num1, i);
		result.number.push_back('0' + (digit1 - digit2 + 10));
	}
	while (result.number.back() == '0' && (int)result.number.size() > 2) {
		result.number.pop_back();
	}
	return result;
}