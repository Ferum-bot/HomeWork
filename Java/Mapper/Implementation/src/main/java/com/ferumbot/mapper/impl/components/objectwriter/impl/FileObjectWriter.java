package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

import java.io.File;

public class FileObjectWriter implements ObjectWriter<File> {

    private final File file;

    public FileObjectWriter(File file) {
        this.file = file;
    }

    @Override
    public void writeToStart(String string) {

    }

    @Override
    public void writeToEnd(String string) {

    }

    @Override
    public File getWriter() {
        return null;
    }
}
