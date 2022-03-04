package com.ferumbot.mapper.impl.components.objectwriter;

import com.ferumbot.mapper.impl.components.objectwriter.impl.FileObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.OutputStreamObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.StringObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ObjectWriterTest {

    private ObjectWriter<File> fileObjectWriter;
    private ObjectWriter<OutputStream> outputStreamObjectWriter;
    private ObjectWriter<String> stringObjectWriter;

    private File testFile;
    private OutputStream testOutputStream;

    @Test
    void FileObjectWriter_WriteToEnd_SuccessWrite() {

    }

    @Test
    void OutputStreamObjectWriter_WriteToEnd_SuccessWrite() {

    }

    @Test
    void StringObjectWriter_WriteToEnd_SuccessWrite() {

    }

    private void initTestFile(String path) {

    }

    private void initTestOutputStream(String data) {

    }
}