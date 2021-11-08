import random
import string
from random import randrange, uniform
from src.animals.fish.Fish import Location
from src.animals.beast.Beast import BeastType


def random_int():
    return randrange(1000) + 1


def random_small_int():
    return randrange(3) + 1


def random_location():
    location_value = random_small_int()
    if location_value == 1:
        return Location.sea
    elif location_value == 2:
        return Location.lake
    elif location_value == 3:
        return Location.ocean
    else:
        return Location.sea


def random_beast_type():
    beast_type = random_small_int()
    if beast_type == 1:
        return BeastType.predator
    elif beast_type == 2:
        return BeastType.herbivores
    elif beast_type == 3:
        return BeastType.insectivores
    else:
        return BeastType.predator


def random_string(n):
    return ''.join(random.SystemRandom().choice(string.ascii_letters + string.digits) for _ in range(n))
