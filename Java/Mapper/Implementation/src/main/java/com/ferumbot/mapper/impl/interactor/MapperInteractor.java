package com.ferumbot.mapper.impl.interactor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface MapperInteractor {

    <T> T validateStringAndParse(Class<T> clazz, String input);

    <T> T validateStreamAndParse(Class<T> clazz, InputStream stream) throws IOException;

    <T> T validateFileAndParse(Class<T> clazz, File file) throws IOException;

    String validateAndWriteToString(Object object);

    void validateAndWriteToStream(Object object, OutputStream stream) throws IOException;

    void validateAndWriteToFile(Object object, File file) throws IOException;
}
