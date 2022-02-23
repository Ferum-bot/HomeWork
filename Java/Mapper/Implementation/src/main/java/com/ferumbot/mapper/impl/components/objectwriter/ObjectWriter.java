package com.ferumbot.mapper.impl.components.objectwriter;

public interface ObjectWriter <T> {

    void writeToEnd(String string);

    T getWriter();
}
