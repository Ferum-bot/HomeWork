#include <iostream>
#include "graph.h"
#include "gtest/gtest.h"

#include "src/listGraph.h"
#include "src/matrixGraph.h"
#include "src/arcGraph.h"
#include "src/ptrsGraph.h"

#include "src/arcGraph.cpp"
#include "src/listGraph.cpp"
#include "src/matrixGraph.cpp"
#include "src/ptrsGraph.cpp"

#include "src/nodePair.cpp"
#include "src/node.cpp"

#include "src/graphConverter.cpp"

template<typename T>
int32_t Node<T>::minAvailableId = 1e9 + 7;

TEST(IGraph, Creating) {
    // IGraph<int> *listGr = new ListGraph<int>;
    IGraph<int>* arcGr = new ArcGraph<int>;
    // IGraph<int> *matGr = new MatrixGraph<int>;
    // IPtrsGraph<int> *pGraph = new PtrsGraph<int>;
}


int main(int argc, char **argv) {
    //IGraph<int>* arc = new ArcGraph<int>;
}