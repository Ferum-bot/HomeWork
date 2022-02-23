package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

import java.io.File;

public class FileObjectWriter implements ObjectWriter<File> {

    private final File file;

    public FileObjectWriter(File file) {
        this.file = file;
    }

    @Override
    public void writeToEnd(String string) {

    }

    @Override
    public void writeToEnd(Integer string) {

    }

    @Override
    public void writeToEnd(Long string) {

    }

    @Override
    public void writeToEnd(Short string) {

    }

    @Override
    public void writeToEnd(Character string) {

    }

    @Override
    public void writeToEnd(Byte string) {

    }

    @Override
    public void writeToEnd(Float string) {

    }

    @Override
    public void writeToEnd(Double string) {

    }

    @Override
    public void writeToEnd(Boolean string) {

    }

    @Override
    public File getWriter() {
        return null;
    }
}
