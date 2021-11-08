class Container:

    def __init__(self):
        self.animals = []
        self.size = 0

    def set_size(self, size):
        self.size = size

    def get_size(self):
        return self.size

    def read_data(self, input_stream):
        for i in range(self.size):
            params = input_stream.readLine().split()
            if (len(params) <= 0 or len(params) >= )
            type_of_animal = params[0]