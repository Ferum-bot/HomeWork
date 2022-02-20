package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

public class StringObjectWriter implements ObjectWriter<String> {

    private static final int START = 0;

    private final StringBuilder builder = new StringBuilder();

    @Override
    public void writeToStart(String string) {
        builder.insert(START, string);
    }

    @Override
    public void writeToEnd(String string) {
        builder.append(string);
    }

    @Override
    public String getWriter() {
        return builder.toString();
    }
}
