
#ifndef HOMEWORK_1_NODE_PAIR_H
#define HOMEWORK_1_NODE_PAIR_H

#pragma once

#include <utility>

template<typename T>
class NodePair final {
public:

    NodePair() noexcept {
        this->edge = std::pair<int32_t*, int32_t*>(nullptr, nullptr);
        this->weight = nullptr;
    }

    NodePair(const int32_t& firstNode, const int32_t& secondNode, const T& weght) noexcept {
        int32_t* _firstNode = new int32_t(firstNode);
        int32_t* _secondNode = new int32_t(secondNode);
        T* _weight = new T(weght);
        this->edge = std::pair<int32_t*, int32_t*>(_firstNode, _secondNode);
        this->weight = _weight;
    }

    NodePair(const NodePair& pair) noexcept {
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

    ~NodePair() noexcept {
        if (this->valueIsNotEmpty()) {
            this->clearValue();
        }
    }

    int32_t getFirstNode() const {
        if (this->valueIsEmpty()) {
            throw std::invalid_argument("Can't get Node from empty pair!");
        }
        return *this->edge.first;
    }

    int32_t getSecondNode() const {
        if (this->valueIsEmpty()) {
         throw std::invalid_argument("Can't get Node from empty pair!");
        }
        return *this->edge.second;
    }

    NodePair<T>& operator = (const NodePair<T>& other) noexcept {
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

private:

    std::pair<int32_t*, int32_t*> edge;

    T* weight;

    bool valueIsEmpty() const noexcept {
        return this->weight == nullptr && this->edge.first == nullptr && this->edge.second == nullptr;  
    }

    bool valueIsNotEmpty() const noexcept {
        return !this->valueIsEmpty();
    }

    void clearValue() noexcept {
        delete this->weight;
        delete this->edge.first;
        delete this->edge.second;
    }

};

#endif //HOMEWORK_1_NODE_PAIR_H