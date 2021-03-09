#include "nodePair.h"

template<typename T>
NodePair<T>::NodePair() noexcept {
    this->edge = std::pair<int32_t*, int32_t*>(nullptr, nullptr);
    this->weight = nullptr;
}

template<typename T>
NodePair<T>::NodePair(const int32_t& firstNode, const int32_t& secondNode, const T& weght) noexcept {
    int32_t* _firstNode = new int32_t(firstNode);
    int32_t* _secondNode = new int32_t(secondNode);
    T* _weight = new T(weght);
    this->edge = std::pair<int32_t*, int32_t*>(_firstNode, _secondNode);
    this->weight = _weight;
}

template<typename T>
bool NodePair<T>::valueIsEmpty() const noexcept {
    return this->weight == nullptr && this->edge.first == nullptr && this->edge.second == nullptr;  
}

template<typename T>
bool NodePair<T>::valueIsNotEmpty() const noexcept {
    return !this->valueIsEmpty();
}

template<typename T>
void NodePair<T>::clearValue() noexcept {
    delete this->weight;
    delete this->edge.first;
    delete this->edge.second;
}

template<typename T>
NodePair<T>::~NodePair() noexcept {
    if (this->valueIsNotEmpty()) {
        this->clearValue();
    }
}

template<typename T>
int32_t NodePair<T>::getFirstNode() const {
    if (this->valueIsEmpty()) {
        throw std::invalid_argument("Can't get Node from empty pair!");
    }
    return *this->edge.first;
}

template<typename T>
int32_t NodePair<T>::getSecondNode() const {
    if (this->valueIsEmpty()) {
        throw std::invalid_argument("Can't get Node from empty pair!");
    }
    return *this->edge.second;
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
    this->edge.first = new int32_t(*other.edge.first);
    this->edge.second = new int32_t(*other.edge.second);
    this->weight = new T(*other.weight);
}

template<typename T>
NodePair<T>::NodePair(const NodePair& pair) noexcept {
    if (this->valueIsNotEmpty()) {
        this->clearValue();
    }
    if (pair.valueIsEmpty()) {
        return;
    }
    this->edge.first = new int32_t(*pair.edge.first);
    this->edge.second = new int32_t(*pair.edge.second);
    this->weight = new T(*pair.weight);
}