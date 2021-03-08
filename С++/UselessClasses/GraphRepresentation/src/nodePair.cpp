#include "nodePair.h"

template<typename T>
NodePair<T>::NodePair() noexcept {
    Node<T>* firstNode = new Node<T>();
    Node<T>* secondNode = new Node<T>();
    this->value = std::pair<Node<T>*, Node<T>*>(firstNode, secondNode);
}

template<typename T>
NodePair<T>::NodePair(Node<T>* firstNode, Node<T>* secondNode) {
    if (firstNode == nullptr || secondNode == nullptr) {
         throw std::invalid_argument("Can't create pair of nodes with null pointer")
    }
    Node<T>* _firstNode = new Node<T>(firstNode->getValue());
    Node<T>* _secondNode = new Node<T>(secondNode->getValue());
    this->value = std::pair<Node<T>*, Node<T>*>(_firstNode, _secondNode);
}

template<typename T>
NodePair<T>::NodePair(Node<T> firstNode, Node<T> secondNode) noexcept {
    Node<T>* _firstNode = new Node<T>(firstNode.getValue());
    Node<T>* _secondNode = new Node<T>(secondNode.getValue());
    this->value = std::pair<Node<T>*, Node<T>*>(_firstNode, _secondNode);
}

template<typename T>
NodePair<T>::NodePair(T firstValue, T secondValue) noexcept {
    Node<T>* _firstNode = new Node<T>(firstValue);
    Node<T>* _secondNode = new Node<T>(secondValue);
    this->value = std::pair<Node<T>*, Node<T>*>(_firstNode, _secondNode);
}

template<typename T>
NodePair<T>::NodePair(T* firstValue, T* secondValue) {
    if (firstValue == nullptr || secondValue == nullptr) {
        throw std::invalid_argument("Can't create pair of nodes with null pointer")
    }
    Node<T>* _firstNode = new Node<T>(*firstValue);
    Node<T>* _secondNode = new Node<T>(*secondValue);
    this->value = std::pair<Node<T>*, Node<T>*>(_firstNode, _secondNode);
}

template<typename T>
bool NodePair<T>::valueIsEmpty() const noexcept {
    return value.first == nullptr || value.second == nullptr;
}

template<typename T>
bool NodePair<T>::valueIsNotEmpty() const noexcept {
    return !this->valueIsEmpty();
}

template<typename T>
void NodePair<T>::clearValue() noexcept {
    if (this->value.first != nullptr) {
        delete this->value.first;
    }
    if (this->value.second != nullptr) {
        delete this->value.second;
    }
    this->value.first = nullptr;
    this->value.second = nullptr;
}

template<typename T>
NodePair<T>::NodePair(const NodePair& pair) noexcept {
    if (this->valueIsNotEmpty) {
        this->clearValue();
    }
    if (pair.valueIsEmpty()) {
        return;
    }
    Node<T>* firstNode = new Node<T>(pair.getFirstNode());
    Node<T>* secondNode = new Node<T>(pair.getSecondNode());
    this->value.first = firstNode;
    this->value.second = secondNode;
}

template<typename T>
NodePair<T>::~NodePair() noexcept {
    if (this->valueIsEmpty()) {
        return;
    }
    this->clearValue();
}

template<typename T>
Node<T> NodePair<T>::getFirstNode() const {
    if (this->value.first == nullptr) {
        throw std::invalid_argument("Can't get node from empty pair!");
    }
    return *value.first;
}

template<typename T>
Node<T> NodePair<T>::getSecondNode() const {
    if (this->value.second == nullptr) {
        throw std::invalid_argument("Can't get node from empty pair!");
    }
    return *value.second;
}

template<typename T>
Node<T>* NodePair<T>::getLinkFirstNode() const noexcept {
    return value.first;
}

template<typename T>
Node<T>* NodePair<T>::getLinkSecondNode() const noexcept {
    return value.second;
}

template<typename T>
NodePair<T>& NodePair<T>::operator = (const NodePair<T>& other) noexcept {
    if (this == &other) {
        return *this;
    }
    if (this->valueIsNotEmpty()) {
        this->clearValue();
    }
    if (other.valueIsEmpty()) {
        return *this;
    }
    Node<T>* linkToFirstNode = new Node<T>(other.getFirstNode());
    Node<T>* linkToSecondNode = new Node<T>(other.getSecondNode());
    this->value = std::pair<Node<T>*, Node<T>*>(linkToFirstNode, linkToSecondNode);
}