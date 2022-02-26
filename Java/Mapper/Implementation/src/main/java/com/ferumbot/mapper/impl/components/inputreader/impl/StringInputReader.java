package com.ferumbot.mapper.impl.components.inputreader.impl;

import com.ferumbot.mapper.impl.components.inputreader.InputReader;

@SuppressWarnings("ClassCanBeRecord")
public class StringInputReader implements InputReader<String> {

    private final String input;

    public StringInputReader(String input) {
        this.input = input;
    }

    @Override
    public String readAll() {
        return input;
    }

    @Override
    public String getReader() {
        return input;
    }
}
