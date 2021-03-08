#include "node.h"

template<typename T>
Node<T>::Node() noexcept {
    this->weightOfVertice = nullptr;
    this->numberOfVertice = nullptr;
}

template<typename T>
Node<T>::Node(const T& weightOfVertice, const int32_t& numberOfVertice) noexcept {
    this->weightOfVertice = new T(weightOfVertice);
    this->numberOfVertice = new int(numberOfVertice);
}

template<typename T>
Node<T>::Node(const Node<T>& node) noexcept {
    if (this->value != nullptr) {
        delete this->value;
    }
    if (node.value == nullptr) {
        this->value = nullptr;
        return;
    }
    T value = node.getValue();
    this->value = new T(value);
}

template<typename T>
Node<T>::~Node() noexcept {
    if (this->value == nullptr) {
        return;
    }
    delete this->value;
}

template<typename T>
T Node<T>::getValue() const {
    if (this->value == nullptr) {
        throw std::invalid_argument("Can't get value from empty node!");
    }
   return *value;
}

template<typename T>
void Node<T>::setValue(const T& value) noexcept {
    if (this->value != nullptr) {
        delete this->value;
    }
   this->value = new T(value);
}

template<typename T>
T* Node<T>::getLinkToValue() const noexcept {
    return this->value;
}

template<typename T>
Node<T>& Node<T>::operator = (const Node<T>& other) noexcept {
    if (this == &other) {
        return *this;
    }
    if (this->value != nullptr) {
        delete this->value;
    }
    if (other.getLinkToValue() == nullptr) {
        return *this;
    }
    this->value = new T(other.getValue());
}

template<typename T>
bool Node<T>::nodeIsEmpty() const noexcept {
    return this->numberOfVertice == nullptr || this->weightOfVertice == nullptr;
}