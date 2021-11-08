from src.animals.fish.Fish import Fish, get_location_by_name
from src.animals.bird.Bird import Bird
from src.animals.beast.Beast import Beast, get_type_by_name
from src.animals.animal.Animal import Animal
from src.random.Random import random_small_int, random_int, random_string, \
    random_location, random_beast_type


class Container:

    def __init__(self):
        self.animals = []
        self.size = 0

    def set_size(self, size):
        self.size = size

    def get_size(self):
        return self.size

    def read_data(self, input_stream):
        self.size = int(input_stream.readLine())
        actual_size = 0
        for i in range(self.size):
            actual_size += 1
            params = input_stream.readLine().split()
            if len(params) <= 0 or len(params) > 4:
                print("Invalid input")
                exit(1)

            type_of_animal = params[0]
            name = params[1]
            weight = params[2]
            if type_of_animal == 1:
                is_transferable = bool(params[3])
                self.animals.append(Bird(name, weight, is_transferable))
            elif type_of_animal == 2:
                location = get_location_by_name(params[3])
                self.animals.append(Fish(location, name, weight))
            elif type_of_animal == 3:
                beast_type = get_type_by_name(params[3])
                self.animals.append(Beast(name, weight, beast_type))
            else:
                print("Invalid animal type\n")
                actual_size -= 1

        self.size = actual_size

    def random_input(self, input_stream):
        size = int(input_stream.readLine())
        self.size = size
        for i in range(size):
            type = random_small_int()
            name = random_string(10)
            weight = random_int()
            if type == 1:
                is_transferable = bool(random_int() % 2)
                self.animals.append(Bird(name, weight, is_transferable))
            elif type == 2:
                location = random_location()
                self.animals.append(Fish(location, name, weight))
            elif type == 3:
                beast_type = random_beast_type()
                self.animals.append(Beast(name, weight, beast_type))

    def sort_animals(self):
        for i in range(self.size):
            function_value = self.animals[i].calculate_function()
            max_index = 0

            for j in range(self.size - i):
                if self.animals[j].calculate_function() > function_value:
                    function_value = self.animals[j].calculate_function()
                    max_index = j

            current = self.animals[max_index]
            self.animals[max_index] = self.animals[self.size - i - 1]
            self.animals[self.size - i - 1] = current

    def write_data(self, output_stream):
        output_stream.write("Has " + str(self.size) + " animals!\n")
        for i in range(self.size):
            output_stream.write(self.animals[i].get_info_data())
