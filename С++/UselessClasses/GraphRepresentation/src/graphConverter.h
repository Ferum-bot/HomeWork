#pragma once

#include "../graph.h"

#include "arcGraph.h"
#include "matrixGraph.h"
#include "listGraph.h"

class GraphConverter final {
public:

    template<typename T>
    static bool isArcGraph(IGraph<T>* graph) noexcept;

    template<typename T>
    static bool isListGraph(IGraph<T>* graph) noexcept;

    template<typename T>
    static bool isMatrixGraph(IGraph<T>* graph) noexcept;

    template<typename T>
    static ArcGraph<T>* toArcGraph(IGraph<T>* graph);

    template<typename T>
    static ListGraph<T>* toListGraph(IGraph<T>* graph);

    template<typename T>
    static MatrixGraph<T>* toMatrixGraph(IGraph<T>* graph);

    template<typename T>
    static ArcGraph<T>* createListGraphFromArcGraph(ListGraph<T>* listGraph);

    template<typename T>
    static ArcGraph<T>* createMatrixGraphFromArcGraph(MatrixGraph<T>* matrixGraph);

    template<typename T>
    static ListGraph<T>* createArcGraphFromListGraph(ArcGraph<T>* arcGraph);

    template<typename T>
    static ListGraph<T>* createMatrixGraphFromListGraph(MatrixGraph<T>* matrixGraph);

    template<typename T>
    static MatrixGraph<T>* createArcGraphFromMatrixGraph(ArcGraph<T>* arcGraph);

    template<typename T>
    static MatrixGraph<T>* createListGraphFromMatrixGraph(ListGraph<T>* listGraph);

private:

    template<typename T>
    static ArcGraph<T>* createBaseFromListGraph(ListGraph<T>* listGraph);

    template<typename T>
    static ArcGraph<T>* createBaseFromMatrixGraph(MatrixGraph<T>* matrixGraph);

    template<typename T>
    static ListGraph<T>* convertBaseToListGraph(ArcGraph<T>* baseGraph);

    template<typename T>
    static MatrixGraph<T>* convertBaseToMatrixGraph(ArcGraph<T>* baseGraph);

};