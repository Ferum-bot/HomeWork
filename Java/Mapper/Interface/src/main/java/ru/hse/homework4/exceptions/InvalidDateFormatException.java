package ru.hse.homework4.exceptions;

public class InvalidDateFormatException extends IllegalArgumentException {

    public InvalidDateFormatException(String message) {
        super(message);
    }
}
