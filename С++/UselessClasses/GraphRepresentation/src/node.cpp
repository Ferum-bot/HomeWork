#include "node.h"

template<typename T>
Node<T>::Node() noexcept = default;

template<typename T>
Node<T>::~Node() noexcept {
    for (std::pair<Node<T>*, T*> edge : this->edges) { 
        if (edge.first != nullptr) {
            delete edge.first;
        }
        if (edge.second != nullptr) {
            delete edge.second;
        }
    }
}

template<typename T>
std::vector<std::pair<Node<T>*, T*>> Node<T>::getEdges() const noexcept {
    return this->edges;
}

template<typename T>
void Node<T>::addEdge(Node<T>* node, T* weight) noexcept {
    this->edges.push_back(node, weight);
}