package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("ClassCanBeRecord")
public class FileObjectWriter implements ObjectWriter<File> {

    private final File file;

    private final Writer fileWriter;

    public FileObjectWriter(File file) {
        this.file = file;

        try {
            fileWriter = new FileWriter(file, StandardCharsets.UTF_8, true);
        } catch (IOException exception) {
            throw new ru.hse.homework4.exceptions.IOException(exception.getMessage());
        }
    }

    @Override
    public void writeToEnd(String string) {
        try {
            fileWriter.write(string);
        } catch (IOException exception) {
            throw new ru.hse.homework4.exceptions.IOException(exception.getMessage());
        }
    }

    @Override
    public File getWriter() {
        return file;
    }
}
