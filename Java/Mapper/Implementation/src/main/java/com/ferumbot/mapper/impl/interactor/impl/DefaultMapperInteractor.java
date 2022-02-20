package com.ferumbot.mapper.impl.interactor.impl;

import com.ferumbot.mapper.impl.components.filter.DeserializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;
import com.ferumbot.mapper.impl.core.context.MapperContextHolder;
import com.ferumbot.mapper.impl.core.models.MappingSettings;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.interactor.MapperInteractor;
import com.ferumbot.mapper.impl.processor.DeserializationProcessor;
import com.ferumbot.mapper.impl.processor.SerializationProcessor;

import java.io.*;

public class DefaultMapperInteractor implements MapperInteractor {

    private final SerializationFilterChain serializationFilterChain;
    private final DeserializationFilterChain deserializationFilterChain;

    private final SerializationProcessor serializationProcessor;
    private final DeserializationProcessor deserializationProcessor;

    public DefaultMapperInteractor(MappingSettings settings) {
        var context = MapperContextHolder.getContext();
        context.setSettings(settings);

        serializationFilterChain = Injector.provideSerializationFilterChain();
        deserializationFilterChain = Injector.provideDeserializationFilterChain();

        serializationProcessor = Injector.provideSerializationProcessor();
        deserializationProcessor = Injector.provideDeserializationProcessor();
    }

    @Override
    public <T> T validateStringAndParse(Class<T> clazz, String input) {
        var reader = Injector.provideStringInputReader(input);

        deserializationFilterChain.filterInput(reader);
        deserializationFilterChain.filterClass(clazz);

        return deserializationProcessor.deserialize(clazz, reader);
    }

    @Override
    public <T> T validateStreamAndParse(Class<T> clazz, InputStream stream) throws IOException {
        var reader = Injector.provideStreamInputReader(stream);

        deserializationFilterChain.filterInput(reader);
        deserializationFilterChain.filterClass(clazz);

        return deserializationProcessor.deserialize(clazz, reader);
    }

    @Override
    public <T> T validateFileAndParse(Class<T> clazz, File file) throws IOException {
        var reader = Injector.provideFileInputReader(file);

        deserializationFilterChain.filterInput(reader);
        deserializationFilterChain.filterClass(clazz);

        return deserializationProcessor.deserialize(clazz, reader);
    }

    @Override
    public String validateAndWriteToString(Object object) {
        serializationFilterChain.invokeFilters(object);

        var writer = Injector.provideStringObjectWriter();
        serializationProcessor.serialize(object, writer);

        return writer.getWriter();
    }

    @Override
    public void validateAndWriteToStream(Object object, OutputStream stream) throws IOException {
        serializationFilterChain.invokeFilters(object);

        var writer = Injector.provideStreamObjectWriter(stream);
        serializationProcessor.serialize(object, writer);
    }

    @Override
    public void validateAndWriteToFile(Object object, File file) throws IOException {
        serializationFilterChain.invokeFilters(object);

        var writer = Injector.provideFileObjectWriter(file);
        serializationProcessor.serialize(object, writer);
    }
}
