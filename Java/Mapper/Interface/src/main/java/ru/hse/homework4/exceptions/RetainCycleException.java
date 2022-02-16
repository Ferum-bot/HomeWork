package ru.hse.homework4.exceptions;

public class RetainCycleException extends IllegalArgumentException {

    public RetainCycleException(String message) {
        super(message);
    }
}
