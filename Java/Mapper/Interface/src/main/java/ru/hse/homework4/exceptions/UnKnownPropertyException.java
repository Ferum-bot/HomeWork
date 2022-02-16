package ru.hse.homework4.exceptions;

public class UnKnownPropertyException extends IllegalArgumentException {

    public UnKnownPropertyException(String message) {
        super(message);
    }
}
