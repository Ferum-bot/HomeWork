
#ifndef HOMEWORK_1_GRAPH_H
#define HOMEWORK_1_GRAPH_H

#include <vector>
#include <utility>

#include "src/nodePair.h"
#include "src/node.h"

template<typename T>
class IGraph {
public:
    virtual ~IGraph() {}

    IGraph() {};

    IGraph(IGraph* other) {};

    virtual void addEdge(const int& from, const int& to, T &&_obj) = 0;

    virtual int verticesCount() const = 0;

    virtual void getNextVertices(const int& vertex, std::vector<int> &vertices) const = 0;

    virtual void getPrevVertices(const int& vertex, std::vector<int> &vertices) const = 0;

    virtual void deepFirstSearch(const int& vertex, std::vector<int> &vertices) const = 0;

    virtual void breadthFirstSearch(const int& vertex, std::vector<int> &vertices) const = 0;

};

template<typename T = void>
class IPtrsGraph {
public:
    virtual ~IPtrsGraph() {}

    IPtrsGraph() {};

    virtual void AddEdge(Node<T> *from, Node<T> *to, T &&_obj) = 0;

    virtual int VerticesCount() const = 0;

    virtual void GetNextVertices(Node<T> *vertex, std::vector<Node<T> *> &vertices) const = 0;

    virtual void GetPrevVertices(Node<T> *vertex, std::vector<Node<T> *> &vertices) const = 0;

    virtual void DeepFirstSearch(Node<T> *vertex, std::vector<Node<T> *> &vertices) const = 0;

    virtual void BreadthFirstSearch(Node<T> *vertex, std::vector<Node<T> *> &vertices) const = 0;
};

#endif //HOMEWORK_1_GRAPH_H
