package com.ferumbot.mapper.impl.components.inputreader.impl;

import com.ferumbot.mapper.impl.components.inputreader.InputReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputStreamInputReader implements InputReader<InputStream> {

    private final InputStream input;

    private String cachedInput;

    public InputStreamInputReader(InputStream input) {
        this.input = input;
    }

    @Override
    public String readAll() {
        if (cachedInput == null) {
            cachedInput = getStreamString();
        }
        return cachedInput;
    }

    @Override
    public InputStream getReader() {
        return input;
    }

    private String getStreamString() {
        try {
            var bytes = input.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new ru.hse.homework4.exceptions.IOException(exception.getMessage());
        }
    }
}
