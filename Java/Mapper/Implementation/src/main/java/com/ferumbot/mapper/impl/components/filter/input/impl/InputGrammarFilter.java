package com.ferumbot.mapper.impl.components.filter.input.impl;

import com.ferumbot.mapper.impl.components.filter.input.InputMapperFilter;
import com.ferumbot.mapper.impl.components.inputreader.InputReader;

public class InputGrammarFilter implements InputMapperFilter {

    @Override
    public void filter(InputReader<?> inputReader) {
        var input = inputReader.readAll();
        var size = input.length();
        var isFieldValue = false;

        for (int i = 0; i < size; i++) {

        }
    }
}
