from src.animals.animal.Animal import Animal
import enum


class Fish(Animal):

    def __init__(self, location, name, weight):
        super(Fish, self).__init__(name, weight)
        self.location = location
        if location is not Location:
            print("Invalid location!\n")
            print("Setting to default: " + Location.sea.name + "\n")
            self.location = Location.sea

    def get_location(self):
        return self.location

    def get_info_data(self):
        return "This is fish\n" + \
               "The location s " + self.location.name + "\n" + \
               super(Fish, self).get_info_data() + "\n\n"


class Location(enum.Enum):
    sea = "Sea"
    ocean = "Ocean"
    lake = "Lake"


def get_location_by_name(name):
    if name == "Sea":
        return Location.sea
    elif name == "Ocean":
        return Location.ocean
    elif name == "Lake":
        return Location.lake
    else:
        return Location.sea
