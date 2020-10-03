#include "list.h"

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

	//list& list::operator = (const list& other);

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

	//bool list::empty() const;

	size_t list::size() const {
		return this->sz;
	}

	//void list::clear();

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

	//void list::pop_back();

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

	//void list::pop_front();
	//void list::resize(size_t count);
	//void list::swap(list& other);
	//void list::remove(const int& value);
	//void list::unique();
	//void list::sort();

}