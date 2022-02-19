package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

import java.io.OutputStream;

public class OutputStreamObjectWriter implements ObjectWriter<OutputStream> {

    private final OutputStream stream;

    public OutputStreamObjectWriter(OutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void writeToStart(String string) {

    }

    @Override
    public void writeToEnd(String string) {

    }

    @Override
    public OutputStream getWriter() {
        return null;
    }
}
