#pragma once

#include "../graph.h"


template<typename T>
class ListGraph final: public IGraph<T> {
public:

    virtual void AddEdge(int from, int to, T &&element) {};

    ListGraph() {};

    ListGraph(IGraph<T> *_oth) {};

    virtual int VerticesCount() const { return 0; };

    virtual void GetNextVertices(int vertex, std::vector<int> &vertices) const {};

    virtual void GetPrevVertices(int vertex, std::vector<int> &vertices) const {};

    virtual void DeepFirstSearch(int vertex, std::vector<int> &vertices) const {};

    virtual void BreadthFirstSearch(int vertex, std::vector<int> &vertices) const {};
};

