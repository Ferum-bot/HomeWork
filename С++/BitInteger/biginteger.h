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

	void cleaning(BigInteger& num) {
		int n = num.size();
		while (num.number[n] == '0' && n != 1) {
			num.number.pop_back();
			n--;
		}
	}

	void prepare_for_mp(BigInteger& number, int sz) {
		int n = number.size();
		for (int i = n; i < sz; i++) {
			number.number.push_back('0');
		}
		return;
	}

	std::pair<BigInteger, BigInteger> cut(BigInteger& number) {
		int n = number.size();
		BigInteger left, right;
		left.number.pop_back();
		right.number.pop_back();
		for (int i = 1; i <= n / 2; i++) {
			right.number.push_back(number.number[i]);
		}
		for (int i = n / 2 + 1; i <= n; i++) {
			left.number.push_back(number.number[i]);
		}
		return { left, right };
	}

	void add_0(BigInteger& number, int kol) {
		BigInteger num = 0;
		num.number.pop_back();
		for (int i = 0; i < kol; i++) {
			num.number.push_back('0');
		}
		for (int i = 1; i <= number.size(); i++) {
			num.number.push_back(number.number[i]);
		}
		number = num;
		return;
	}

	BigInteger multiplication(BigInteger f_num, BigInteger s_num) {
		int sz1 = f_num.size();
		int sz2 = s_num.size();
		if (sz1 == 1 && sz2 == 1) {
			int val1 = f_num.number[1] - '0';
			int val2 = s_num.number[1] - '0';
			int res_val = val1 * val2;
			BigInteger res = res_val;
			return res;
		}
		int sz = sz1 >= sz2 ? sz1 : sz2;
		if (sz % 2 != 0) {
			sz++;
		}
		prepare_for_mp(f_num, sz);
		prepare_for_mp(s_num, sz);
		std::pair<BigInteger, BigInteger> pr1 = cut(f_num);
		std::pair<BigInteger, BigInteger> pr2 = cut(s_num);
		BigInteger l_f_num = pr1.first;
		BigInteger r_f_num = pr1.second;
		BigInteger l_s_num = pr2.first;
		BigInteger r_s_num = pr2.second;
		BigInteger val1 = multiplication(l_f_num, l_s_num);
		BigInteger val2 = multiplication(r_f_num, r_s_num);
		cleaning(l_f_num), cleaning(r_f_num);
		cleaning(l_s_num), cleaning(r_s_num);
		BigInteger sum1 = l_f_num + r_f_num;
		BigInteger sum2 = l_s_num + r_s_num;
		BigInteger val3 = multiplication(sum1, sum2);
		cleaning(val1), cleaning(val2), cleaning(val3);
		BigInteger val4 = val3 - val2 - val1;
		cleaning(val4);
		add_0(val1, sz);
		add_0(val4, sz / 2);
		return (val1 + val4 + val2);

	}

	friend int get_digit(const BigInteger& number, const int& id);
	friend BigInteger addition(const BigInteger& first_num, const BigInteger& second_num);
	friend void recalc(BigInteger& number, int pos);
	friend BigInteger subtraction(BigInteger& first_num, BigInteger& second_num);

	void add_number(BigInteger& num, char val) {
		BigInteger res = (val - '0');
		int n = num.size();
		for (int i = 1; i <= n; i++) {
			res.number.push_back(num.number[i]);
		}
		cleaning(res);
		num = res;
		return;
	}

	BigInteger division(BigInteger& f_num, BigInteger& s_num) {
		int pos = f_num.size();
		BigInteger cur_num;
		BigInteger answer;
		cur_num.number.pop_back();
		answer.number.pop_back();
		int n = s_num.size() - 1;
		while (n != 0) {
			add_number(cur_num, f_num.number[pos]);
			pos--;
			n--;
		}
		while (pos != 0) {
			add_number(cur_num, f_num.number[pos]);
			pos--;
			char digit = '0';
			while (cur_num >= s_num) {
				cur_num -= s_num;
				digit++;
			}
			add_number(answer, digit);
		}
		return answer;
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

	~BigInteger() = default;

	BigInteger& operator = (const int& number) {
		this->number = make_number(number);
		return *this;
	}

	friend std::istream& operator >>(std::istream& cin, BigInteger& number);
	friend std::ostream& operator <<(std::ostream& cout, const BigInteger& number);

	friend std::string to_string(const BigInteger& number);

	int size() {
		return ((int)this->number.size() - 1);
	}

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


	BigInteger operator + (const BigInteger& number) {
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

	BigInteger operator -(const BigInteger& number) {
		bool sign1 = this->number[0] == '+' ? 1 : 0;
		bool sign2 = number.number[0] == '+' ? 1 : 0;
		BigInteger f_num = *this;
		BigInteger s_num = number;
		if (sign1 && sign2) {
			return subtraction(f_num, s_num);
		}
		if (sign1 && !sign2) {
			return addition(f_num, s_num);
		}
		if (!sign1 && sign2) {
			BigInteger cur_num2 = number;
			cur_num2.number[0] = '-';
			return addition(f_num, cur_num2);
		}
		if (!sign1 && !sign2) {
			return subtraction(s_num, f_num);
		}
	}

	BigInteger operator -()  {
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

	BigInteger operator +=(const BigInteger& number) {
		*this = *this + number;
		return *this;
	}

	BigInteger operator -=(const BigInteger& number) {
		*this = *this - number;
		return *this;
	}

	BigInteger& operator ++() {
		if (this->number[0] == '+') {
			int n = this->number.size();
			int fn = -1;
			for (int i = 1; i < n; i++) {
				if (this->number[i] == '9') {
					continue;
				}
				fn = i;
				break;
			}
			if (fn == -1) {
				this->number.push_back('1');
				fn = n;
			}
			else {
				this->number[fn]++;
			}
			for (int i = 1; i < fn; i++) {
				this->number[i] = '0';
			}
			return *this;
		}
		else {
			int n = this->number.size();
			int fn = -1;
			for (int i = 1; i < n; i++) {
				if (this->number[i] == '0') {
					continue;
				}
				fn = i;
				break;
			}
			if (fn == 1 && this->number[1] == '1' && fn + 1 == n) {
				this->number[0] = '+';
				this->number[1] = '0';
				return *this;
			}
			if (this->number[fn] == 1 && fn + 1 == n) {
				this->number.pop_back();
			}
			else {
				this->number[fn]--;
			}
			for (int i = 1; i < fn; i++) {
				this->number[i] = '9';
			}
			return *this;
		}
	}

	BigInteger& operator --() {
		int n = this->number.size();
		if (this->number[0] == '+') {
			int fn = -1;
			for (int i = 1; i < n; i++) {
				if (this->number[i] == '0') {
					continue;
				}
				fn = i;
				break;
			}
			if (fn == -1) {
				this->number[0] = '-';
				this->number[1] = '1';
				return *this;
			}
			if (fn + 1 == n && this->number[fn] == '1' && n != 2) {
				this->number.pop_back();
			}
			else {
				this->number[fn]--;
			}
			for (int i = 1; i < fn; i++) {
				this->number[i] = '9';
			}
			return *this;
		}
		else {
			int fn = -1;
			for (int i = 1; i < n; i++) {
				if (this->number[i] == '9') {
					continue;
				}
				fn = i;
				break;
			}
			if (fn == -1) {
				fn = n;
				this->number.push_back('1');
			}
			else {
				this->number[fn]++;
			}
			for (int i = 1; i < fn; i++) {
				this->number[i] = '0';
			}
			return *this;
		}
	}

	BigInteger operator ++(int) {
		BigInteger result = *this;
		++(*this);
		return result;
	}

	BigInteger operator --(int) {
		BigInteger result = *this;
		--(*this);
		return result;
	}

	BigInteger operator *(const BigInteger& number) {
		BigInteger left = *this;
		BigInteger right = number;
		bool sign1 = left.number[0] == '+' ? 1 : 0;
		bool sign2 = right.number[0] == '+' ? 1 : 0;
		left.number[0] = '+';
		right.number[0] = '+';
		BigInteger result = multiplication(left, right);
		if (sign1 != sign2) {
			result.number[0] = '-';
		}
		int n = result.size();
		while (result.number[n] == '0' && n > 1) {
			result.number.pop_back();
			n--;
		}
		return result;
	}
	
	BigInteger operator /(const BigInteger& number) {
		BigInteger f_num = *this;
		BigInteger s_num = number;
		bool sign1 = f_num.number[0] == '+' ? 1 : 0;
		bool sign2 = s_num.number[0] == '+' ? 1 : 0;
		f_num.number[0] = '+';
		s_num.number[0] = '+';
		if (f_num < s_num) {
			BigInteger res = 0;
			return res;
		}
		BigInteger res = division(f_num, s_num);
		if (sign1 != sign2) {
			res.number[0] = '-';
		}
		return res;
	}

	BigInteger operator *= (const BigInteger& number) {
		*this = *this * number;
		return *this;
	}

	BigInteger operator /= (const BigInteger& number) {
		*this = *this / number;
		return *this;
	}

	BigInteger operator % (const BigInteger& number) {
		BigInteger s_num = *this;
		BigInteger f_num = number;
		BigInteger res = s_num - s_num / f_num * f_num;
		return res;
	}

	BigInteger operator %= (const BigInteger & number) {
		*this = *this % number;
		return *this;
	}
	
	operator bool() const {
		std::vector<char> cur = { '+', '0' };
		return number == cur ? 0 : 1;
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
	std::cout << num1 << ' ' << num2 << "\n";
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
