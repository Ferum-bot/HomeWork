package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

public class StringObjectWriter implements ObjectWriter<String> {

    private final StringBuilder builder = new StringBuilder();

    @Override
    public void writeToStart(String string) {

    }

    @Override
    public void writeToEnd(String string) {

    }

    @Override
    public String getWriter() {
        return null;
    }
}
