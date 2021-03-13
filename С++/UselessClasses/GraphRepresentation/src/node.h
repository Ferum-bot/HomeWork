#pragma once

template<typename T>
class Node final {
public:

    Node() noexcept;
    ~Node() noexcept;

    std::vector<std::pair<Node<T>*, T*>> getEdges() const noexcept;

    void addEdge(Node<T>* node, T* weight) noexcept;

private:

    std::vector<std::pair<Node<T>*, T*>> edges;

};