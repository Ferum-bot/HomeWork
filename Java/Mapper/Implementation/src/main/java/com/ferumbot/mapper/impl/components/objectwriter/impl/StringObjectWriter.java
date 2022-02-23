package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

public class StringObjectWriter implements ObjectWriter<String> {

    private static final int START = 0;

    private final StringBuilder builder = new StringBuilder();

    @Override
    public void writeToEnd(String string) {
        builder.append(string);
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
    public String getWriter() {
        return builder.toString();
    }
}
