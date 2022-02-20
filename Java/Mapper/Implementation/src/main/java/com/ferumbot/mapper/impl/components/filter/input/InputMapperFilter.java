package com.ferumbot.mapper.impl.components.filter.input;

import com.ferumbot.mapper.impl.components.inputreader.InputReader;

import java.io.File;
import java.io.InputStream;

public interface InputMapperFilter {

    void filter(InputReader<?> input);
}
