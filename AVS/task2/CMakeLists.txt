cmake_minimum_required(VERSION 3.20)
project(new_seminar1)

set(CMAKE_CXX_STANDARD 14)

add_executable(new_seminar1 main.cpp src/container/Container.cpp src/container/Container.h src/animals/animal/Animal.cpp src/animals/animal/Animal.h)
