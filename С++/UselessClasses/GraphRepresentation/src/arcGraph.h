
#ifndef HOMEWORK_1_ARCGRAPH_H
#define HOMEWORK_1_ARCGRAPH_H

#include "../graph.h"


template<typename T = void>
class ArcGraph final: public IGraph<T> {
public:

    ArcGraph();

    ArcGraph(IGraph<T> *_oth);

    ArcGraph(const ArcGraph& graph);

    ~ArcGraph();

    virtual void addEdge(const int32_t& from, const int32_t& to, T &&element) override;

    virtual int verticesCount() override;

    virtual void getNextVertices(int vertex, std::vector<int> &vertices) override;

    virtual void getPrevVertices(int vertex, std::vector<int> &vertices) override;

    virtual void deepFirstSearch(int vertex, std::vector<int> &vertices) override;

    virtual void breadthFirstSearch(int vertex, std::vector<int> &vertices) override;

    ArcGraph<T>& operator = (const ArcGraph<T>& other) noexcept;

private:
    
    std::vector<NodePair<T>*> pairsOfVertices;

    void clearValue() noexcept;

    bool isValueEmpty() const noexcept;
    bool isValueNotEmpty() const noexcept;

};

#endif //HOMEWORK_1_ARCGRAPH_H
