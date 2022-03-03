package com.ferumbot.mapper.impl.components.inputreader.impl;

import com.ferumbot.mapper.impl.components.inputreader.InputReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileInputReader implements InputReader<File> {

    private final File input;

    private String cachedInput;

    public FileInputReader(File input) {
        this.input = input;
    }

    @Override
    public String readAll() {
        if (cachedInput == null) {
            cachedInput = getFileString();
        }
        return cachedInput;
    }

    @Override
    public File getReader() {
        return input;
    }

    private String getFileString() {
        try {
            var path = input.toPath();
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new ru.hse.homework4.exceptions.IOException(exception.getMessage());
        }
    }
}
