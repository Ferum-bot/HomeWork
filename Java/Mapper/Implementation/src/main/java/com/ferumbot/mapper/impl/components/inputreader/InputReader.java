package com.ferumbot.mapper.impl.components.inputreader;

import java.io.IOException;

public interface InputReader <T> {

    String readAll() throws IOException;

    T getReader();
}
