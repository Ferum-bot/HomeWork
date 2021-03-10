
#ifndef HOMEWORK_1_ARCGRAPH_H
#define HOMEWORK_1_ARCGRAPH_H

#pragma once

#include "../graph.h"

#include "graphConverter.h"


template<typename T>
class ArcGraph final: public IGraph<T> {
public:

    ArcGraph();

    ArcGraph(IGraph<T>* other);

    ArcGraph(const ArcGraph& graph) {
        if (this->isValueNotEmpty()) {
            this->clearValue();
        }
        if (graph.isValueEmpty()) {
            return;
        }
        for (NodePair<T>* pair: graph.pairsOfVertices) {
            NodePair<T>* copyPair = new NodePair<T>(*pair);
            this->pairsOfVertices.push_back(copyPair);
        }
    }

    ~ArcGraph() {
        if (this->isValueEmpty()) {
            return;
        }
        this->clearValue();
    }

    virtual void addEdge(const int32_t& from, const int32_t& to, T &&element) override {
        NodePair<T>* edge = new NodePair<T>(from, to, element);
        this->pairsOfVertices.push_back(edge);
    }

    virtual int verticesCount() const override {
        return pairsOfVertices.size();
    }

    virtual void getNextVertices(const int& vertex, std::vector<int> &vertices) const override;

    virtual void getPrevVertices(const int& vertex, std::vector<int> &vertices) const override;

    virtual void deepFirstSearch(const int& vertex, std::vector<int> &vertices) const override;

    virtual void breadthFirstSearch(const int& vertex, std::vector<int> &vertices) const override;

    ArcGraph<T>& operator = (const ArcGraph<T>& other) noexcept {
        if (this == &other) {
            return *this;
        }
        if (this->isValueNotEmpty()) {
            this->clearValue();
        }
        if (other.isValueEmpty()) {
            return *this;
        }
        for (NodePair<T>* pair: other.pairsOfVertices) {
            NodePair<T>* copyPair = new NodePair<T>(*pair);
            this->pairsOfVertices.push_back(copyPair);
        }
        return *this;
    }

private:
    
    std::vector<NodePair<T>*> pairsOfVertices;

    void clearValue() noexcept {
        for (auto& node: pairsOfVertices) {
            delete node;
        }
        pairsOfVertices.clear();
        pairsOfVertices.shrink_to_fit();
    }

    bool isValueEmpty() const noexcept {
        return this->pairsOfVertices.empty();
    }

    bool isValueNotEmpty() const noexcept {
        return !this->isValueEmpty();
    }


};

#endif //HOMEWORK_1_ARCGRAPH_H
