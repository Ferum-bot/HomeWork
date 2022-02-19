package com.ferumbot.mapper.impl.components.objectwriter;

public interface ObjectWriter <T> {

    void writeToStart(String string);

    void writeToEnd(String string);

    T getWriter();
}
