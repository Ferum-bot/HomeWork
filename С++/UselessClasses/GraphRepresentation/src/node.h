#pragma once

template<typename T>
class Node final {
public:

    Node() noexcept;
    Node(const T& weightOfVertice, const int32_t& numberOfVertice) noexcept;
    Node(const Node<T>& node) noexcept;
    ~Node() noexcept;

    void setWeightOfVertice(const T& value) noexcept;
    void setNumberOfVerice(const int32_t& number) noexcept;

    T getWeightOfVertice() const;
    int32_t getNumberOfVertice() const;

    T* getLinkToWeight() const noexcept;
    int32_t* getLinkToNumber() const noexcept;

    Node<T>& operator = (const Node<T>& other) noexcept;

private:

    T* weightOfVertice;

    int32_t* numberOfVertice;

    bool nodeIsEmpty() const noexcept;
    bool nodeIsNotEmpty() const noexcept;

    void clearNode() noexcept;

};