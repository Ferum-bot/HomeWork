#include "arcGraph.h"
#include "matrixGraph.h"
#include "listGraph.h"

#include "graphConverter.h"

template<typename T>
ArcGraph<T>::ArcGraph(IGraph<T>* other) {
    if (this->isValueNotEmpty()) {
        this->clearValue();
    }
    if (GraphConverter::isArcGraph(other)) {
        ArcGraph<T>* arcGraph = GraphConverter::toArcGraph(other);
        this = arcGraph->getCopy();
        return;
    }
    if(GraphConverter::isListGraph(other)) {
        ListGraph<T>* listGraph = GraphConverter::toListGraph(other);
        this = GraphConverter::createArcGraphFromListGraph(listGraph);
        return;
    }
    if (GraphConverter::isMatrixGraph(other)) {
        MatrixGraph<T>* matrixGraph = GraphConverter::toMatrixGraph(other);
        this = GraphConverter::createArcGraphFromMatrixGraph(matrixGraph);
        return;
    }
}

template<typename T>
ArcGraph<T>::ArcGraph(const ArcGraph& graph) {
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

template<typename T>
ArcGraph<T>::~ArcGraph() {
    if (this->isValueEmpty()) {
        return;
    }
    this->clearValue();
}

template<typename T>
void ArcGraph<T>::addEdge(const int32_t& from, const int32_t& to, T &&element){
    NodePair<T>* edge = new NodePair<T>(from, to, element);
    this->pairsOfVertices.push_back(edge);
}

template<typename T>
int ArcGraph<T>::verticesCount() {
    return pairsOfVertices.size();
}

template<typename T>
void ArcGraph<T>::getNextVertices(const int32_t& vertex, std::vector<int32_t> &vertices) {
    vertices.clear();
    for (NodePair<T>* nodePair : this->pairsOfVertices) {
        if (nodePair->getFirstNode() == vertex) {
            vertices.push_back(nodePair->getSecondNode());
        }
    }
}

template<typename T>
void ArcGraph<T>::getPrevVertices(const int32_t& vertex, std::vector<int32_t> &vertices) {
    vertices.clear();
    for (NodePair<T>* nodePair : this->pairsOfVertices) {
        if (nodePair->getSecondNode() == vertex) {
            vertices.push_back(nodePair->getFirstNode());
        }
    }
}

template<typename T>
void ArcGraph<T>::deepFirstSearch(const int32_t& vertex, std::vector<int32_t> &vertices) {

}

template<typename T>
void ArcGraph<T>::breadthFirstSearch(const int32_t& vertex, std::vector<int32_t> &vertices) {

}

template<typename T>
ArcGraph<T>& ArcGraph<T>::operator = (const ArcGraph<T>& other) noexcept {
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

template<typename T>
void ArcGraph<T>::clearValue() noexcept {
    for (NodePair<T>* nodePair: pairsOfVertices) {
        delete nodePair;
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
ArcGraph<T>* ArcGraph<T>::getCopy() const noexcept {
    ArcGraph<T>* resultCopy = new ArcGraph<T>();
    for (NodePair<T>* nodePair : this->pairsOfVertices) {
        resultCopy->addEdge(nodePair->getFirstNode(), nodePair->getSecondNode(), nodePair->getWeight());
    } 
    return resultCopy;
}