﻿#include "list.h"

namespace task {

	list::Node::Node() {
		this->value = 0;
		this->next_node = nullptr;
		this->prev_node = nullptr;
		return;
	}
	
	list::Node::Node(const int& value, Node* next, Node* prev) {
		this->value = value;
		this->next_node = next;
		this->prev_node = prev;
	}
	
	list::Node::Node(const Node& node) = default;
	
	list::Node::~Node() {
		delete next_node;
	}

	list::list() {
		this->sz = 0;
		this->head = nullptr;
		this->tail = nullptr;
	}

	list::list(size_t count, const int& value) {
		this->sz = count;
		this->head = new Node(value);
		this->tail = new Node();
		this->head->next_node = this->tail;
		this->tail->prev_node = this->head;
		for (int i = 1; i < static_cast<int>(count); i++) {
			this->tail->value = value;
			this->tail->next_node = new Node();
			this->tail->next_node->prev_node = this->tail;
			this->tail = this->tail->next_node;
		}
	}

	
	list::~list() {
		delete head;
	}

	list& list::operator = (const list& other) {
		this->clear();
		int n = static_cast<int>(other.size());
		if (n == 0) {
			return *this;
		}
		Node* curr = other.head;
		this->head = new Node(curr->value);
		this->tail = new Node();
		this->head->next_node = this->tail;
		this->tail->prev_node = this->head;
		for (int i = 1; i < n; i++) {
			curr = curr->next_node;
			this->push_back(curr->value);
		}
		return *this;
	}

	int& list::front() {
		return this->head->value;
	}

	const int& list::front() const {
		return this->head->value;
	}
	
	int& list::back() {
		return this->tail->prev_node->value;
	}
	
	const int& list::back() const {
		return this->tail->prev_node->value;
	}

	bool list::empty() const {
		if (this->size() == 0) {
			return true;
		}
		return false;
	}

	size_t list::size() const {
		return this->sz;
	}

	void list::clear() {
		this->sz = 0;
		delete head;
		this->head = nullptr;
		this->tail = nullptr;
	}

	void list::push_back(const int& value) {
		this->sz += 1;
		if (this->head == nullptr) {
			this->head = new Node(value);
			this->tail = new Node();
			this->tail->prev_node = this->head;
			this->head->next_node = this->tail;
		}
		else {
			this->tail->value = value;
			this->tail->next_node = new Node();
			this->tail->next_node->prev_node = this->tail;
			this->tail = this->tail->next_node;
		}
		return;
	}

	void list::pop_back() {
		if (this->empty()) {
			return;
		}
		this->sz--;
		if (this->size() == 0) {
			this->clear();
			return;
		}
		Node* curr = this->tail->prev_node;
		this->tail->prev_node = nullptr;
		delete this->tail;
		curr->next_node = nullptr;
		curr->value = 0;
		this->tail = curr;
	}

	void list::push_front(const int& value) {
		if (this->head == nullptr) {
			this->push_back(value);
			return;
		}
		this->sz += 1;
		Node* curr = new Node(value);
		curr->next_node = this->head;
		this->head->prev_node = curr;
		this->head = curr;
	}

	void list::pop_front() {
		if (this->empty()) {
			return;
		}
		this->sz--;
		if (this->sz == 0) {
			this->clear();
			return;
		}
		Node* curr = this->head->next_node;
		this->head->next_node = nullptr;
		delete this->head;
		this->head = curr;
	}

	void list::resize(size_t count) {
		if (this->size() <= count) {
			int n = static_cast<int>(count - this->size());
			for (int i = 0; i < n; i++) {
				this->push_back(0);
			}
			return;
		}
		else {
			int n = static_cast<int>(this->size() - count);
			for (int i = 0; i < n; i++) {
				this->pop_back();
			}
			return;
		}
	}
	
	bool list::check(const int& value) const  {
		if (this->empty()) {
			return false;
		}
		int n = this->size();
		Node* curr = this->head;
		for (int i = 0; i < n; i++) {
			if (curr->value == value) {
				return true;
			}
			curr = curr->next_node;
		}
		return false;
	}

	//void list::swap(list& other);
	
	void list::remove(const int& value) {
		if (!this->check(value)) {
			return;
		}
		list res;
		int n = static_cast<int>(this->size());
		Node* curr = this->head;
		for (int i = 0; i < n; i++) {
			if (curr->value != value) {
				res.push_back(curr->value);
			}
			curr = curr->next_node;
		}
		this->clear();
		(*this) = res;
	}
	
	//void list::unique();
	
	void list::sort() {
		if (this->empty() || this->size() == 1) {
			return;
		}

	}

}