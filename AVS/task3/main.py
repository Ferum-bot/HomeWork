import sys
import time

from src.container.Container import Container


def invalid_params():
    print("Invalid input parameters!\n")
    print("See readme file!\n")


def incorrect_flag():
    print("Invalid flag\n")
    print("See readme file!\n")


def fill_container(container):
    if sys.argv[1] == "-f":
        input_stream = open(sys.argv[2], 'r')
        container.read_data(input_stream)
        return

    if sys.argv[1] == "-g":
        input_stream = open(sys.argv[2], 'r')
        container.random_input(input_stream)
        return

    incorrect_flag()
    exit(2)


if __name__ == '__main__':
    if len(sys.argv) != 5:
        invalid_params()
        exit(1)

    start_time = time.time()
    print("Program starts\n")

    container = Container()
    print("Container created\n")

    fill_container(container)
    print("Container filled\n")

    output_stream = open(sys.argv[3], 'w')
    sorted_output_stream = open(sys.argv[4], 'w')
    container.write_data(output_stream)
    print("Container printed\n")

    container.sort_animals()
    container.write_data(sorted_output_stream)
    print("Container sorted\n")

    print("Program ends\n")

    print("Program work time: " + str(round(time.time() - start_time, 3)))
