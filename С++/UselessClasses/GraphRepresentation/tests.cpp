#include <iostream>
#include "graph.h"
#include "gtest/gtest.h"

#include "src/listGraph.h"
#include "src/matrixGraph.h"
#include "src/arcGraph.h"
#include "src/ptrsGraph.h"

#include "src/arcGraph.cpp"

TEST(IGraph, Creating) {
    // IGraph<int> *listGr = new ListGraph<int>;
    IGraph<int>* arcGr = new ArcGraph<int>;
    // IGraph<int> *matGr = new MatrixGraph<int>;
    // IPtrsGraph<int> *pGraph = new PtrsGraph<int>;
}


int main(int argc, char **argv) {
    //IGraph<int>* arc = new ArcGraph<int>;

    IGraph<int>* arcGr = new ArcGraph<int>;
    for (int i = 0; i < 10; i++) {
        arcGr->addEdge(i, i + 1, 100);
    }
    std::cout << arcGr->verticesCount();
    return 0;
}