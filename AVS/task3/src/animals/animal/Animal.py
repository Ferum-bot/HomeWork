class Animal:

    def __init__(self, name, weight):
        self.name = name
        self.weight = weight
        if weight < 0 or weight > 1000:
            print("Incorrect weight of animal!\n")
            print("Set default value 10\n")
            self.weight = 10

    def get_name(self):
        return self.name

    def get_weight(self):
        return self.weight

    def calculate_function(self):
        sum_of_codes = int(0)
        length = len(self.name)
        for i in range(length):
            sum_of_codes += int(str(self.name)[i])
        return float(sum_of_codes) / float(self.weight)

    def get_info_data(self):
        return "The weight is " + str(self.weight) + "\n" + \
                "The name is " + str(self.name) + "\n"
