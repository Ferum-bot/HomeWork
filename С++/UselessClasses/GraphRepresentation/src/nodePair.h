
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
    NodePair(Node<T>* firstNode, Node<T>* secondNode);
    NodePair(Node<T> firstNode, Node<T> secondNode) noexcept;
    NodePair(T firstValue, T secondValue) noexcept;
    NodePair(T* firstValue, T* secondValue);
    NodePair(const NodePair& pair) noexcept;
    ~NodePair() noexcept;

    Node<T> getFirstNode() const;
    Node<T> getSecondNode() const;

    Node<T>* getLinkFirstNode() const noexcept;
    Node<T>* getLinkSecondNode() const noexcept;

    NodePair<T>& operator = (const NodePair<T>& other) noexcept;

private:

    std::pair<Node<T>*, Node<T>*> value;

    bool valueIsEmpty() const noexcept;
    bool valueIsNotEmpty() const noexcept;

    void clearValue() noexcept;

};

#endif //HOMEWORK_1_NODE_PAIR_H