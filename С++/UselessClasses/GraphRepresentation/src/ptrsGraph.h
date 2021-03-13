#pragma once

#include "node.h"
#include "../graph.h"

template<typename T = void>
class PtrsGraph final : public IPtrsGraph<T> {

public:

    PtrsGraph();

    ~PtrsGraph();

    virtual void addEdge(Node<T> *from, Node<T> *to, T &&_obj) override;

    virtual int verticesCount() const;

    virtual void getNextVertices(Node<T> *vertex, std::vector<Node<T> *> &vertices) override;

    virtual void getPrevVertices(Node<T> *vertex, std::vector<Node<T> *> &vertices) override;

    virtual void deepFirstSearch(Node<T> *vertex, std::vector<Node<T> *> &vertices) override;

    virtual void breadthFirstSearch(Node<T> *vertex, std::vector<Node<T> *> &vertices) override;

private:

    std::vector<Node<T>*> nodes;

    bool isValueEmpty() const noexcept;
};
