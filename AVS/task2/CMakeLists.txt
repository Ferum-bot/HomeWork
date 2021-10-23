cmake_minimum_required(VERSION 3.11)
project(task2)

set(CMAKE_CXX_STANDARD 11)

add_executable(task2 main.cpp
        src/container/Container.cpp
        src/container/Container.h
        src/animals/animal/Animal.cpp
        src/animals/animal/Animal.h
        src/animals/beast/Beast.h
        src/animals/beast/Beast.cpp
        src/animals/bird/Bird.h
        src/animals/bird/Bird.cpp
        src/animals/fish/Fish.h
        src/animals/fish/Fish.cpp
        src/controller/TaskController.h
        src/controller/TaskController.cpp
        src/random/RandomUtil.h
        src/random/RandomUtil.cpp
        src/sorter/ContainerSorter.h
        src/sorter/impl/StraightSelectorSorter.h
        src/sorter/impl/StraightSelectorSorter.cpp
        src/ui/input/InputProvider.h
        src/ui/input/file/FileInputProvider.h
        src/ui/input/file/FileInputProvider.cpp
        src/ui/input/random/RandomInputProvider.h
        src/ui/input/random/RandomInputProvider.cpp
        src/ui/output/OutputProvider.h
        src/ui/output/file/FileOutputProvider.h
        src/ui/output/file/FileOutputProvider.cpp
)
