from src.animals.animal.Animal import Animal
import enum


class Beast(Animal):

    def __init__(self, name, weight, type):
        super(Beast, self).__init__(name, weight)
        self.type = type
        if type is not BeastType:
            print("Invalid beast type\n")
            print("Setting type to default: " + BeastType.predator.name)
            self.type = BeastType.predator

    def get_type(self):
        return self.type

    def get_info_data(self):
        return "This is predator\n" + \
                "The type is " + self.type.name + \
                super(Beast, self).get_info_data() + "\n\n"


class BeastType(enum.Enum):
    predator = "Predator"
    herbivores = "Herbivores"
    insectivores = "Insectivores"


def get_type_by_name(name):
    if name == "Predator":
        return BeastType.predator
    elif name == "Herbivores":
        return BeastType.herbivores
    elif name == "Insectivores":
        return BeastType.insectivores
    else:
        return BeastType.predator
