package ru.hse.homework4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Serialized and deserialized objects.
 * @see DateFormat
 * @see Exported
 * @see Ignored
 * @see PropertyName
 * @apiNote Supports records and retain identity.
 * @author matvejpopov
 * @version 1.0.0
 */
public interface Mapper {

    /**
     * Reads saved object of class T from string
     * and returns the deserialized object.
     * @param clazz the class of deserialized object.
     * @param input the string to deserialize from.
     * @param <T> the type of deserialized object.
     * @return deserialized class object.
     */
    <T> T readFromString(Class<T> clazz, String input);

    /**
     * Reads saved object of class T from input stream
     * and returns the deserialized object.
     * @apiNote string in input stream must be in {@link StandardCharsets#UTF_8} encoding.
     * @param clazz the class of deserialized object.
     * @param stream the input stream to deserialize from.
     * @param <T> the type of deserialized object.
     * @return deserialized class object.
     * @throws IOException in case of input-output error.
     */
    <T> T read(Class<T> clazz, InputStream stream) throws IOException;

    /**
     * Reads saved object of class T from file
     * and returns the deserialized object.
     * @apiNote content in the file must be string in {@link StandardCharsets#UTF_8} encoding.
     * @param clazz the class of deserialized object.
     * @param file the file to deserialize from.
     * @param <T> the type of deserialized object.
     * @return deserialized class object.
     * @throws IOException in case of input-output error.
     */
    <T> T read(Class<T> clazz, File file) throws IOException;

    /**
     * Saves object to string.
     * @param object the object to serialize.
     * @return serialized object.
     * @throws IOException in case of input-output error.
     */
    String writeToString(Object object) throws IOException;

    /**
     * Saves object to output stream.
     * @param object the object to serialize.
     * @param stream the output stream to serialize where.
     * @throws IOException in case of input-output error.
     */
    void write(Object object, OutputStream stream) throws IOException;

    /**
     * Saves object to file.
     * @param object the object to serialize.
     * @param file the file to serialize where.
     * @throws IOException in case of input-output error.
     */
    void write(Object object, File file) throws IOException;
}
