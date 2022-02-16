package ru.hse.homework4.exceptions;

import java.util.IllegalFormatException;

public class InvalidDateFormatException extends IllegalArgumentException {

    public InvalidDateFormatException(String message) {
        super(message);
    }
}
