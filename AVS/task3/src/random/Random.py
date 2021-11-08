import random
import string
from math import ceil
from random import randrange, uniform


def random_int():
    return randrange(1000) + 1


def random_small_int():
    return randrange(3) + 1


def random_string(n):
    return ''.join(random.SystemRandom().choice(string.ascii_letters + string.digits) for _ in range(n))
