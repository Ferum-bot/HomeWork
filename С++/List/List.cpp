#include "list.h"

namespace task {

	list::Node::Node() {
		this->value = 0;
		this->next_node = nullptr;
	}
	
	list::Node::Node(const int& value, Node* next) {
		this->value = value;
		this->next_node = next;
	}
	
	list::Node::Node(const Node& node) = default;
	
	list::Node::~Node() {
		delete next_node;
	}

	list::list() {
		this->sz = 0;
		this->head = nullptr;
	}

	list::list(size_t count, const int& value) {
		this->sz = count;
		this->head = new Node(value);
		Node* curr = this->head;
		for (int i = 1; i < static_cast<int>(count); i++) {
			curr->next_node = new Node(value);
			curr = curr->next_node;
		}
	}

	
	list::~list() {
		delete head;
	}

	//list list::operator=(const list& other);

	int& list::front() {
		return this->head->value;
	}

	const int& list::front() const {
		return this->head->value;
	}
	
	/*
	int& list::back();
	const int& list::back() const;
	bool list::empty() const;
	size_t list::size() const;
	void list::clear();
	*/

	void list::push_back(const int& value) {
		this->sz += size_t(1);
		if (this->head == nullptr) {
			this->head = new Node(value);
		}
		else {
			Node * curr = this->head;
			while (curr->next_node != nullptr) {
				curr = curr->next_node;
			}
			curr->next_node = new Node(value);
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