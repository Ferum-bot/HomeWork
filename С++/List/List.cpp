#include "list.h"

namespace task {
	/*
	list::Node::Node();
	list::Node::Node(const int& value, Node& next = nullptr);
	list::Node::Node(const Node& node);
	list::Node::~Node();
	*/

	list::list() {
		this->sz = 0;
		this->head = nullptr;
	}

	/*list::list(size_t count, const int& value = int()) {
		this->sz = count;
		for (int i = 0; i < static_cast<int>(this->sz); i++) {
			 
		}
	}*/

	/*
	list::~list();
	list list::operator=(const list& other);
	int& list::front();
	const int& list::front() const;
	int& list::back();
	const int& list::back() const;
	bool list::empty() const;
	size_t list::size() const;
	void list::clear();
	*/

	void list::push_back(const int& value) {
		if (this->head == nullptr) {
			//this->head = new Node(value);
		}
		else {
			
		}
	}

	/*
	void list::pop_back();
	void list::push_front(const int& value);
	void list::pop_front();
	void list::resize(size_t count);
	void list::swap(list& other);
	void list::remove(const int& value);
	void list::unique();
	void list::sort();
	*/

}