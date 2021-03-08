
#ifndef HOMEWORK_1_NODE_PAIR_H
#define HOMEWORK_1_NODE_PAIR_H

#pragma once

#include <utility>

#include "node.h"

template<typename T>
class NodePair final {
public:

    NodePair();
    NodePair(const NodePair& pair);
    NodePair(Node<T>* firstNode, Node<T>* secondNode);
    ~NodePair();

    Node<T>* getFirstNode() const;
    Node<T>* getSecondNode() const;

private:

    std::pair<Node<T>*, Node<T>*> value;

};

#endif //HOMEWORK_1_NODE_PAIR_H