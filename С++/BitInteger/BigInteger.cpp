#include "biginteger.h"

int BigInteger::size() const {
    return (int)this->original_number.size() - 1;
}

bool BigInteger::sign() const {
    if (this->original_number[0] == '+') {
        return true;
    }
    return false;
}

void BigInteger::push_back(int value) {
    char digit = '0' + value;
    this->push_back(digit);
    return;
}

void BigInteger::push_back(char value) {
    this->original_number.push_back(value);
    return;
}

void BigInteger::pop_back() {
    this->original_number.pop_back();
    return;
}

void BigInteger::pop_front() {
    BigInteger curr;
    curr.pop_back();
    int n = this->size();
    for (int i = 2; i <= n; i++) {
        curr.push_back((*this)[i]);
    }
    (*this) = curr;
    return;
}

void BigInteger::push_front(int value) {
    char digit = '0' + value;
    this->push_front(digit);
    return;
}

void BigInteger::push_front(char value) {
    BigInteger curr = (value - '0');
    int n = this->size();
    for (int i = 1; i <= n; i++) {
        curr.push_back((*this)[i]);
    }
    (*this) = curr;
    return;
}

char& BigInteger::operator [] (int index) {
    return this->original_number[index];
}

std::vector<char> BigInteger::make_number(int value) {
    std::vector<char> result;
    if (value == 0) {
        result = { '+', '0' };
        return result;
    }
    if (value > 0) {
        result.push_back('+');
    }
    else {
        result.push_back('-');
    }
    value = value < 0 ? -value : value;
    while (value != 0) {
        char digit = '0' + (value % 10);
        result.push_back(digit);
        value /= 10;
    }
    return result;
}

std::vector<char> BigInteger::make_number(std::string& value) {
    std::vector<char> result;
    if (value[0] == '-') {
        result.push_back('-');
    }
    else {
        result.push_back('+');
    }
    int n = (int)value.size();
    for (int i = n - 1; i >= 0; i--) {
        if (value[i] == '-' || value[i] == '+') {
            break;
        }
        result.push_back(value[i]);
    }
    return result;
}

BigInteger::BigInteger() {
    this->original_number = make_number(0);
    return;
}

BigInteger::BigInteger(int value) {
    this->original_number = make_number(value);
}

BigInteger::BigInteger(const BigInteger& value) = default;

BigInteger::~BigInteger() = default;

BigInteger& BigInteger::operator = (int value) {
    this->original_number = make_number(value);
    return (*this);
}

bool BigInteger::operator == (BigInteger& value) {
    if (this->sign() != value.sign()) {
        return false;
    }
    if (this->size() != value.size()) {
        return false;
    }
    int n = this->size();
    for (int i = 1; i <= n; i++) {
        if ((*this)[i] != value[i]) {
            return false;
        }
    }
    return true;
}

bool BigInteger::operator != (BigInteger& value) {
    return !((*this) == value);
}

bool BigInteger::operator > (BigInteger& value) {
    if (*this == value) {
        return false;
    }
    if (this->sign() > value.sign()) {
        return true;
    }
    if (this->sign() < value.sign()) {
        return false;
    }
    if (this->sign()) {
        if (this->size() > value.size()) {
            return true;
        }
        if (this->size() < value.size()) {
            return false;
        }
        int n = this->size();
        for (int i = n; i > 0; i--) {
            if ((*this)[i] == value[i]) {
                continue;
            }
            if ((*this)[i] > value[i]) {
                return true;
            }
            return false;
        }
        return false;
    }
    if (this->size() > value.size()) {
        return false;
    }
    if (this->size() < value.size()) {
        return true;
    }
    int n = this->size();
    for (int i = n; i > 0; i--) {
        if ((*this)[i] == value[i]) {
            continue;
        }
        if ((*this)[i] > value[i]) {
            return false;
        }
        return true;
    }
    return false;
}

bool BigInteger::operator >= (BigInteger& value) {
    return (*this) == value || (*this) > value;
}

bool BigInteger::operator < (BigInteger& value) {
    return !((*this) >= value);
}

bool BigInteger::operator <= (BigInteger& value) {
    return !((*this) > value);
}

BigInteger BigInteger::operator + (const BigInteger& value) {
    BigInteger left = *this;
    BigInteger right = value;
    if (left.sign() && right.sign()) {
        return addition(left, right);
    }
    if (!left.sign() && !right.sign()) {
        return addition(left, right);
    }
    if (left.sign() && !right.sign()) {
        right[0] = '+';
        return subtraction(left, right);
    }
    left[0] = '+';
    return subtraction(right, left);
}

BigInteger BigInteger::operator - (const BigInteger& value) {
    BigInteger left = *this;
    BigInteger right = value;
    if (left.sign() && right.sign()) {
        return subtraction(left, right);
    }
    if (left.sign() && !right.sign()) {
        right[0] = '+';
        return addition(left, right);
    }
    if (!left.sign() && right.sign()) {
        right[0] = '-';
        return addition(left, right);
    }
    right[0] = '+';
    left[0] = '+';
    return subtraction(right, left);
}

/*
BigInteger BigInteger::operator * (const BigInteger& value);
BigInteger BigInteger::operator / (const BigInteger& value);
BigInteger BigInteger::operator % (const BigInteger& value);
*/


// i dont sure about & after first BigIneger
BigInteger& BigInteger::operator += (const BigInteger& value) {
    (*this) = (*this) + value;
    return (*this);
}

BigInteger& BigInteger::operator -= (const BigInteger& value) {
    (*this) = (*this) - value;
    return (*this);
}

/*
BigInteger& BigInteger::operator *= (const BigInteger& value);
BigInteger& BigInteger::operator /= (const BigInteger& value);
BigInteger& BigInteger::operator %= (const BigInteger& value);
*/

BigInteger& BigInteger::operator --() {
    if (this->sign()) {
        int n = this->size();
        BigInteger tmp = 0;
        if ((*this) == tmp) {
            (*this) = -1;
            return *this;
        }
        int st = 1;
        for (int i = 1; i <= n; i++) {
            if ((*this)[i] == '0') {
                continue;
            }
            st = i;
            break;
        }
        if ((*this)[st] == '1' && st == n && st != 1) {
            this->pop_back();
        }
        else {
            (*this)[st]--;
        }
        for (int i = st - 1; i > 0; i--) {
            (*this)[i] = '9';
        }
        return (*this);
    }
    int n = this->size();
    int st = -1;
    for (int i = 1; i <= n; i++) {
        if ((*this)[i] == '9') {
            continue;
        }
        st = i;
        break;
    }
    if (st == -1) {
        this->push_back(1);
        st = n + 1;
    }
    else {
        (*this)[st]++;
    }
    for (int i = st - 1; i > 0; i--) {
        (*this)[i] = '0';
    }
    return *this;
}

BigInteger& BigInteger::operator ++() {
    if (this->sign()) {
        int n = this->size();
        int st = -1;
        for (int i = 1; i <= n; i++) {
            if ((*this)[i] == '9') {
                continue;
            }
            st = i;
            break;
        }
        if (st == -1) {
            this->push_back(1);
            st = n + 1;
        }
        else {
            (*this)[st]++;
        }
        for (int i = st - 1; i > 0; i--) {
            (*this)[i] = '0';
        }
        return (*this);
    }
    int n = this->size();
    BigInteger value = -1;
    if ((*this) == value) {
        (*this) = 0;
        return (*this);
    }
    int st = 1;
    for (int i = 1; i <= n; i++) {
        if ((*this)[i] == '0') {
            continue;
        }
        st = i;
        break;
    }
    (*this)[st]--;
    if ((*this)[st] == '0' && st == n) {
        this->pop_back();
    }
    for (int i = st - 1; i > 0; i--) {
        (*this)[i] = '9';
    }
    return (*this);
}

BigInteger BigInteger::operator --(int) {
    BigInteger tmp = *this;
    --(*this);
    return tmp;
}

BigInteger BigInteger::operator ++(int) {
    BigInteger tmp = *this;
    ++(*this);
    return tmp;
}

BigInteger BigInteger::operator -() {
    BigInteger cur_num = *this;
    if (cur_num == BigInteger(0)) {
        return cur_num;
    }
    if (this->sign()) {
        cur_num[0] = '-';
    }
    else {
        cur_num[0] = '+';
    }
    return cur_num;
}

BigInteger::operator bool() const {
    std::vector<char> value = {'+', '0'};
    return original_number != value;
}

std::string BigInteger::toString() {
    std::string res = "";
    if (!this->sign()) {
        res = "-";
    }
    int n = this->size();
    for (int i = n; i > 0; i--) {
        res += (*this)[i];
    }
    return res;
}

std::istream& operator >> (std::istream& in, BigInteger& value) {
    std::string s;
    in >> s;
    value.original_number = value.make_number(s);
    return in;
}

std::ostream& operator << (std::ostream& out, const BigInteger& value) {
    BigInteger tmp = value;
    out << tmp.toString();
    return out;
}

// dont forget to delete this line in Release
std::string to_string(BigInteger& number){
    std::string res = number.toString();
    return res;
}

int get_digit(BigInteger& num, int index) {
    if (num.size() < index) {
        return 0;
    }
    int digit = num[index] - '0';
    return digit;
}

BigInteger addition(BigInteger& left_value, BigInteger& right_value) {
    BigInteger res = 0;
    res.pop_back();
    if (!left_value.sign()) {
        res[0] = '-';
    }
    int n = left_value.size() > right_value.size() ? left_value.size() : right_value.size();
    int add = 0;
    for (int i = 1; i <= n; i++) {
        int left_digit = get_digit(left_value, i);
        int right_digit = get_digit(right_value, i);
        int sum = left_digit + right_digit + add;
        res.push_back(sum % 10);
        add = sum / 10;
    }
    if (add != 0) {
        res.push_back(add);
    }
    return res;
}

void recalc(BigInteger& num, int index) {
    int n = num.size();
    int st = index + 1;
    for (int i = index + 1; i <= n; i++) {
        if (num[i] == '0') {
            continue;
        }
        num[i]--;
        st = i;
        break;
    }
    for (int i = index + 1; i < st; i++) {
        num[i] = '9';
    }
    return;
}

BigInteger subtraction(BigInteger& left_value, BigInteger& right_value) {
    if (left_value == right_value) {
        BigInteger res = 0;
        return res;
    }
    if (left_value < right_value) {
        BigInteger res = subtraction(right_value, left_value);
        res[0] = '-';
        return res;
    }
    BigInteger res = 0;
    res.pop_back();
    int n = right_value.size() > left_value.size() ? right_value.size() : left_value.size();
    for (int i = 1; i <= n; i++) {
        int left_digit = get_digit(left_value, i);
        int right_digit = get_digit(right_value, i);
        if (left_digit >= right_digit) {
            res.push_back(left_digit - right_digit);
            continue;
        }
        recalc(left_value, i);
        res.push_back(left_digit - right_digit + 10);
    }
    while (res[n] == '0' && n > 0) {
        res.pop_back();
        n--;
    }
    return res;
}
