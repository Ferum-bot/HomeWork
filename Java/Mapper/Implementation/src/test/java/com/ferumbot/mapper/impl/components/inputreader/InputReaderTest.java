package com.ferumbot.mapper.impl.components.inputreader;

import com.ferumbot.mapper.impl.components.inputreader.impl.FileInputReader;
import com.ferumbot.mapper.impl.components.inputreader.impl.InputStreamInputReader;
import com.ferumbot.mapper.impl.components.inputreader.impl.StringInputReader;
import com.ferumbot.mapper.impl.util.FileUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    private InputReader<File> fileInputReader;
    private InputReader<InputStream> inputStreamInputReader;
    private InputReader<String> stringInputReader;

    private File testFile;
    private InputStream testInputStream;
    private String testString;

    @BeforeEach
    void setUp() {
        fileInputReader = new FileInputReader(testFile);
        inputStreamInputReader = new InputStreamInputReader(testInputStream);
        stringInputReader = new StringInputReader(testString);
    }

    @AfterEach
    void tearDown() {
        fileInputReader = null;
        inputStreamInputReader = null;
        stringInputReader = null;
    }

    @Test
    void FileInputReader_ReadAll_SuccessRead() throws URISyntaxException {

    }

    @Test
    void InputStreamInputReader_ReadAll_SuccessRead() {

    }

    @Test
    void StringInputReader_ReadAll_SuccessRead() {

    }

    private void initTestFile(String path) throws URISyntaxException {
        testFile = FileUtil.getFileFromTestResource(path);
    }

    private void initTestInputReader(String data) {

    }

    private void initTestString(String data) {

    }

}