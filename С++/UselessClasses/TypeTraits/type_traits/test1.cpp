
#include <type_traits>

template<typename T, typename Y>
struct is_same  {

    const static value = false

};

template<typename T>
struct is_same<T, T>  {

    const static value = true;

};


signed main() {
    
    

    return 0;
}