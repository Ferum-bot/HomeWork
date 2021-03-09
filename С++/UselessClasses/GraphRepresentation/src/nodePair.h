
#ifndef HOMEWORK_1_NODE_PAIR_H
#define HOMEWORK_1_NODE_PAIR_H

#pragma once

#include <utility>

#include "node.h"
#include "node.cpp"

template<typename T>
class NodePair final {
public:

    NodePair() noexcept;
    NodePair(const int32_t& firstNode, const int32_t& secondNode, const T& weght) noexcept;
    NodePair(const NodePair& pair) noexcept;
    ~NodePair() noexcept;

    int32_t getFirstNode() const;
    int32_t getSecondNode() const;

    NodePair<T>& operator = (const NodePair<T>& other) noexcept;

private:

    std::pair<int32_t*, int32_t*> edge;

    T* weight;

    bool valueIsEmpty() const noexcept;
    bool valueIsNotEmpty() const noexcept;

    void clearValue() noexcept;

};

#endif //HOMEWORK_1_NODE_PAIR_H