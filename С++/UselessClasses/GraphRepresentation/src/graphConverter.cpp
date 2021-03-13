#include "graphConverter.h"

template<typename T>
bool GraphConverter::isArcGraph(IGraph<T>* graph) noexcept {
    ArcGraph<T>* arcGraph = dynamic_cast<ArcGraph<T>*>(graph);
    return arcGraph != nullptr;
}

template<typename T>
bool GraphConverter::isListGraph(IGraph<T>* graph) noexcept {
    ListGraph<T>* listGraph = dynamic_cast<ListGraph<T>*>(graph);
    return listGraph != nullptr;
}

template<typename T>
bool GraphConverter::isMatrixGraph(IGraph<T>* graph) noexcept {
    MatrixGraph<T>* matrixGraph = dynamic_cast<MatrixGraph<T>*>(graph);
    return matrixGraph != nullptr;
}

template<typename T>
ListGraph<T>* GraphConverter::toListGraph(IGraph<T>* graph) {
    if (!GraphConverter::isListGraph(graph)) {
        throw std::invalid_argument("Can't convert no list graph to list graph. Use checkers to check what type of graph is!");
    }
    ListGraph<T>* resultGraph = dynamic_cast<ListGraph<T>*>(graph);
    return resultGraph;
}

template<typename T>
ArcGraph<T>* GraphConverter::toArcGraph(IGraph<T>* graph) {
    if (!GraphConverter::isArcGraph(graph)) {
        throw std::invalid_argument("Can't convert no arc graph to arc graph. Use checkers to check what type of graph is!");
    }
    ArcGraph<T>* resultGraph = dynamic_cast<ArcGraph<T>*>(graph);
    return resultGraph;
}

template<typename T>
MatrixGraph<T>* GraphConverter::toMatrixGraph(IGraph<T>* graph) {
    if (!GraphConverter::isMatrixGraph(graph)) {
        throw std::invalid_argument("Can't convert no matrix graph to matrix graph. Use checkers to check what type of graph is!");
    }
    MatrixGraph<T>* resultGraph = dynamic_cast<MatrixGraph<T>*>(graph);
    return resultGraph;
}
