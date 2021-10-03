cmake_minimum_required(VERSION 3.11)

project(AVS_Task1)

set(CMAKE_CXX_STANDARD 11)

set(SRC_LIST main.cpp src/animals/bird/Bird.cpp src/animals/fish/Fish.cpp src/animals/beast/Beast.cpp src/animals/animal/Animal.cpp src/container/Container.cpp src/random/Random.cpp)

add_executable(${PROJECT_NAME} ${SRC_LIST})