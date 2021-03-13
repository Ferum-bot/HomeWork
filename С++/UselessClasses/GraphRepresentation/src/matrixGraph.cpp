#include "arcGraph.h"
#include "matrixGraph.h"
#include "listGraph.h"

#include "graphConverter.h"

template<typename T>
MatrixGraph<T>::MatrixGraph() = default;

template<typename T>
MatrixGraph<T>::MatrixGraph(IGraph<T> *oth) {
    if (this->valueIsNotEmpty()) {
        this->clearValue();
    }
    if (GraphConverter::isArcGraph(oth)) {
        ArcGraph<T>* arcGraph = GraphConverter::toArcGraph(oth);
        this = GraphConverter::createMatrixGraphFromArcGraph(arcGraph);
        return;
    }
    if (GraphConverter::isMatrixGraph(oth)) {
        MatrixGraph<T>* matrixGraph = GraphConverter::toMatrixGraph(oth);
        this = matrixGraph->getCopy();
        return;
    }
    if (GraphConverter::isListGraph(oth)) {
        ListGraph<T>* listGraph = GraphConverter::toListGraph(oth);
        this = GraphConverter::createMatrixGraphFromListGraph(listGraph);
        return;
    }
}

template<typename T>
MatrixGraph<T>::MatrixGraph(const MatrixGraph<T>& other) {
    if (this->valueIsNotEmpty()) {
        this->clearValue();
    }
    if (other.valueIsEmpty()) {
        return;
    }
    const int32_t size = other.matrix.size();
    this->matrix.resize(size);
    for (int32_t v = 0; v < size; v++) {
        this->matrix.resize(size);
        for (int32_t u = 0; u < size; u++) {
            if (other.matrix[v][u] == nullptr) {
                this->matrix[v][u] = nullptr;
                continue;
            }
            this->matrix[v][u] = new T(*other.matrix[v][u]);
        }
    }
}

template<typename T>
MatrixGraph<T>::~MatrixGraph() {
    if (this->valueIsNotEmpty()) {
        this->clearValue();
    }
}

template<typename T>
void MatrixGraph<T>::AddEdge(const int32_t& from, const int& to, T &&element) {
    const int32_t currentSize = this->matrix.size();
    const int32_t maxVertex = std::max(from, to);
    if (maxVertex >= currentSize) {
        this->matrix.resize(maxVertex + 1);
    }
    const int32_t newSize = this->matrix.size();
    for (int32_t v = currentSize; v < newSize; v++) {
        for (int32_t u = currentSize; u < newSize; u++) {
            this->matrix[v][u] = nullptr;
        }
    }
    this->matrix[from][to] = new T(element);
}

template<typename T>
int MatrixGraph<T>::verticesCount() {
    const int32_t size = this->matrix.size();
    std::map<int32_t, bool> used;
    for (int32_t v = 0; v < size; v++) {
        for (int32_t u = 0; u < size; u++) {
            if (this->matrix[v][u] != nullptr) {
                used[v] = true;
                used[u] = true;
            }
        }
    }
    return used.size();
}

template<typename T>
void MatrixGraph<T>::getNextVertices(const int32_t& vertex, std::vector<int32_t> &vertices) {
    if (vertex >= this->matrix.size()) {
        return;
    }
    const int32_t size = this->matrix.size();
    for (int32_t u = 0; u < size; u++) {
        if (matrix[vertex][u] != nullptr) {
            vertices.push_back(u);
        }
    }
}

template<typename T>
void MatrixGraph<T>::getPrevVertices(const int32_t& vertex, std::vector<int32_t> &vertices) {
    if (vertex >= this->matrix.size()) {
        return;
    }
    const int32_t size = this->matrix.size();
    for (int32_t u = 0; u < size; u++) {
        if (matrix[u][vertex] != nullptr) {
            vertices.push_back(u);
        }
    }
}

template<typename T>
void MatrixGraph<T>::deepFirstSearch(const int32_t& vertex, std::vector<int32_t> &vertices) {
    ListGraph<T>* listGraphCopy = GraphConverter::createListGraphFromMatrixGraph(this);
    listGraphCopy->deepFirstSearch(vertex, vertices);
    delete listGraphCopy;
}

template<typename T>
void MatrixGraph<T>::breadthFirstSearch(const int32_t& vertex, std::vector<int32_t> &vertices) {
    ListGraph<T>* listGraphCopy = GraphConverter::createListGraphFromMatrixGraph(this);
    listGraphCopy->breadthFirstSearch(vertex, vertices);
    delete listGraphCopy;
}

template<typename T>
std::vector<std::vector<T*>> MatrixGraph<T>::getMatrix() const noexcept {
    return this->matrix;
}

template<typename T>
void MatrixGraph<T>::clearValue() {
    const int32_t size = this->matrix.size();
    for (int32_t v = 0; v < size; v++) {
        for (int32_t u = 0; u < size; u++) {
            if (matrix[v][u] != nullptr) {
                delete matrix[v][u];
            }
        }
    }
}

template<typename T>
bool MatrixGraph<T>::valueIsEmpty() const noexcept {
    return !this->valueIsNotEmpty();
}

template<typename T>
bool MatrixGraph<T>::valueIsNotEmpty() const noexcept {
    const int32_t size = this->matrix.size();
    for (int32_t v = 0; v < size; v++) {
        for (int32_t u = 0; u < size; u++) {
            if (matrix[v][u] != nullptr) {
                return true;
            }
        }
    }
    return false;
}

template<typename T>
MatrixGraph<T>* MatrixGraph<T>::getCopy() const noexcept {
    MatrixGraph<T>* copy = new MatrixGraph();
    const int32_t size = this->matrix.size();
    for (int32_t v = 0; v < size; v++) {
        for (int32_t u = 0; u < size; u++) {
            if (matrix[v][u] == nullptr) {
                copy->matrix[v][u] = nullptr;
                continue;
            }
            copy->matrix[v][u] = new T(*matrix[v][u]);
        }
    }
    return copy;
}