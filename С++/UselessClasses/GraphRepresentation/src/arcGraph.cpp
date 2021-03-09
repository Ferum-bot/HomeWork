#include "arcGraph.h"

template<typename T>
int ArcGraph<T>::verticesCount() const {
    return pairsOfVertices.size();
}

template<typename T>
ArcGraph<T>::~ArcGraph() {
    if (this->isValueEmpty()) {
        return;
    }
    this->clearValue();
}

template<typename T>
void ArcGraph<T>::clearValue() {
    for (auto& node: pairsOfVertices) {
        delete node;
    }
    pairsOfVertices.clear();
    pairsOfVertices.shrink_to_fit();
}

template<typename T>
bool ArcGraph<T>::isValueEmpty() const noexcept {
    return this->pairsOfVertices.empty();
}

template<typename T>
bool ArcGraph<T>::isValueNotEmpty() const noexcept {
    return !this->isValueEmpty();
}

template<typename T>
ArcGraph<T>& ArcGraph<T>::operator = (const ArcGraph<T>& other) noexcept {
    if (this == &other) {
        return;
    }
    if (this->isValueNotEmpty()) {
        this->clearValue();
    }
    if (other.isValueEmpty()) {
        return;
    }
    for (NodePair<T>* pair: other.pairsOfVertices) {
        NodePair<T>* copyPair = new NodePair<T>(pair);
        this->pairsOfVertices.push_back(copyPair);
    }
}
