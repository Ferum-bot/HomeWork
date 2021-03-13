#include "arcGraph.h"
#include "matrixGraph.h"
#include "listGraph.h"

#include "graphConverter.h"

#include <set>

template<typename T>
PtrsGraph<T>::PtrsGraph() = default;

template<typename T>
PtrsGraph<T>::~PtrsGraph() {
    this->clearValue();
}

template<typename T>
void PtrsGraph<T>::addEdge(Node<T> *from, Node<T> *to, T &&obj) {
    T* weight = new T(obj);
    this->edges.push_back(from, to, weight);
}

template<typename T>
int PtrsGraph<T>::verticesCount() const {
    const std::set<Node<T>*> used;
    for (const std::tuple<Node<T>*, Node<T>*, T*> edge : this->edjes) {
        used.insert(edge.get(0));
        used.insert(edge.get(1));
    }
    return used.size();
}

template<typename T>
void PtrsGraph<T>::getNextVertices(Node<T> *vertex, std::vector<Node<T> *> &vertices) {
    vertices.clear();
    vertices.shrink_to_fit();
    for (const std::tuple<Node<T>*, Node<T>*, T*> edge : this->edjes) {
        if (edge.get(0) == vertex) {
            vertices.push_back(edge.get(1));
        }
    }
}

template<typename T>
void PtrsGraph<T>::getPrevVertices(Node<T> *vertex, std::vector<Node<T> *> &vertices) {
    vertices.clear();
    vertices.shrink_to_fit();
    for (const std::tuple<Node<T>*, Node<T>*, T*> edge : this->edjes) {
        if (edge.get(1) == vertex) {
            vertices.push_back(edge.get(0));
        }
    }
}

template<typename T>
void PtrsGraph<T>::deepFirstSearch(Node<T> *vertex, std::vector<Node<T> *> &vertices) {

}

template<typename T>
void PtrsGraph<T>::breadthFirstSearch(Node<T> *vertex, std::vector<Node<T> *> &vertices) {

}

template<typename T>
void PtrsGraph<T>::clearValue() {
    const std::set<Node<T>*> used;
    for (const std::tuple<Node<T>*, Node<T>*, T*> edge : this->edjes) {
        delete edge.get(2);
        used.insert(edge.get(0));
        used.insert(edge.get(1));
    }
    for (auto& el : used) {
        delete el;
    }
}

template<typename T>
ListGraph<T>* PtrsGraph<T>::createListGraph() const noexcept {
    ListGraph<T>* resultGraph = new ListGraph<T>();
    for (const std::tuple<Node<T>*, Node<T>*, T*> edge : this->edjes) {
        int32_t firstNode = edge.get(0)->getIndex();
        int32_t secondNode = edge.get(1)->getIndex();
        resultGraph->addEdge(firstNode, secondNode, edge.get(2)->getWeight());
    }
    return resultGraph;
}