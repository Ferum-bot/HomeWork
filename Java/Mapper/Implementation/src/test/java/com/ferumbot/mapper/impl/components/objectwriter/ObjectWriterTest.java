package com.ferumbot.mapper.impl.components.objectwriter;

import com.ferumbot.mapper.impl.components.objectwriter.impl.FileObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.OutputStreamObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.StringObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ObjectWriterTest {

    private ObjectWriter<File> fileObjectWriter;
    private ObjectWriter<OutputStream> outputStreamObjectWriter;
    private ObjectWriter<String> stringObjectWriter;

    private File testFile;
    private OutputStream testOutputStream;

    @BeforeEach
    void setUp() {
        fileObjectWriter = new FileObjectWriter(testFile);
        outputStreamObjectWriter = new OutputStreamObjectWriter(testOutputStream);
        stringObjectWriter = new StringObjectWriter();
    }

    @AfterEach
    void tearDown() {

    }
}