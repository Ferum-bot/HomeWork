#pragma once

#include "listGraph.h"
#include "matrixGraph.h"
#include "arcGraph.h"
#include "ptrsGraph.h"


template<typename T>
class GraphConverter final {
public:

    bool isArcGraph(IGraph<T>* graph);
    bool isListGraph(IGraph<T>* graph);
    bool isMatrixGraph(IGraph<T>* graph);

    ArcGraph<T>* convertListGraphToAcrGraph(IGraph<T>* graph);
    ArcGraph<T>* convertMatrixGraphToArcGraph(IGraph<T>* graph);
    ArcGraph<T>* convertPtrsGraphToArcGraph(IGraph<T>* graph);

    MatrixGraph<T>* convertListGraphToMatrixGraph(IGraph<T>* graph);
    MatrixGraph<T>* convertArcGraphToMatrixGraph(IGraph<T>* graph);
    MatrixGraph<T>* convertPtrsGraphToMatrixGraph(IGraph<T>* graph);

    ListGraph<T>* convertArcGraphToListGraph(IGraph<T>* graph);
    ListGraph<T>* convertMatrixGraphToListGraph(IGraph<T>* graph);
    ListGraph<T>* convertPtrsGraphToListGraph(IGraph<T>* graph);

    PtrsGraph<T>* convertListGraphToPtrsGraph(IGraph<T>* graph);
    PtrsGraph<T>* convertMatrixGraphToPtrsGraph(IGraph<T>* graph);
    PtrsGraph<T>* convertArcGraphToPtrsGraph(IGraph<T>* graph);

private:

    ArcGraph<T>* convertListGraphToBase(ListGraph<T>* graph);
    ArcGraph<T>* convertMatrixGraphToBase(MatrixGraph<T>* graph);
    ArcGraph<T>* convertPtrsGraphToBase(PtrsGraph<T>* graph);

    ListGraph<T>* convertBaseToListGraph(ArcGraph<T>* graph);
    MatrixGraph<T>* convertBaseToMatrixGraph(ArcGraph<T>* graph);
    PtrsGraph<T>* convertBaseToPtrsGraph(ArcGraph<T>* graph);

};