package com.ferumbot.mapper.impl.components.objectwriter.impl;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("ClassCanBeRecord")
public class OutputStreamObjectWriter implements ObjectWriter<OutputStream> {

    private final OutputStream stream;

    private final Writer streamWriter;

    public OutputStreamObjectWriter(OutputStream stream) {
        this.stream = stream;

        streamWriter = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
    }

    @Override
    public void writeToEnd(String string) {
        try {
            streamWriter.write(string);
        } catch (IOException exception) {
            throw new ru.hse.homework4.exceptions.IOException(exception.getMessage());
        }
    }

    @Override
    public OutputStream getWriter() {
        return stream;
    }
}
