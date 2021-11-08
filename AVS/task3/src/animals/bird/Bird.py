from src.animals.animal.Animal import Animal


class Bird(Animal):

    def __init__(self, name, weight, is_transferable):
        super(Bird, self).__init__(name, weight)
        self.is_transferable = is_transferable
        if is_transferable is not bool:
            print("Invalid is transferable parameter\n")
            print("Setting parameter to default: true\n")
            self.is_transferable = True

    def get_is_transferable(self):
        return self.is_transferable

    def get_info_data(self):
        return "This is is bird\n" + \
               "Is transferable " + str(self.is_transferable) + "\n" + \
               super(Bird, self).get_info_data()
