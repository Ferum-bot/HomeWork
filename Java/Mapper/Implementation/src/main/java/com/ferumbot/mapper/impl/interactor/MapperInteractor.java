package com.ferumbot.mapper.impl.interactor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.ferumbot.mapper.impl.DefaultMapper;
import com.ferumbot.mapper.impl.processor.*;

/**
 * Layer between public interface and processors.
 * Entry point for all mapper processes.
 * @author matvejpopov
 * @version 1.0.0
 * @see DefaultMapper
 * @see SerializationProcessor
 * @see DeserializationProcessor
 */
public interface MapperInteractor {

    <T> T validateStringAndParse(Class<T> clazz, String input);

    <T> T validateStreamAndParse(Class<T> clazz, InputStream stream);

    <T> T validateFileAndParse(Class<T> clazz, File file);

    String validateAndWriteToString(Object object);

    void validateAndWriteToStream(Object object, OutputStream stream);

    void validateAndWriteToFile(Object object, File file);
}
