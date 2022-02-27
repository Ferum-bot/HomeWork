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
import java.util.Map;

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
        var context = MapperContextHolder.getContext();
        context.clearObjectGraph();

        deserializationFilterChain.filterInput(reader);
        deserializationFilterChain.filterClass(clazz);

        return deserializationProcessor.deserialize(clazz, reader);
    }

    @Override
    public <T> T validateStreamAndParse(Class<T> clazz, InputStream stream) {
        var reader = Injector.provideStreamInputReader(stream);
        var context = MapperContextHolder.getContext();
        context.clearObjectGraph();

        deserializationFilterChain.filterInput(reader);
        deserializationFilterChain.filterClass(clazz);

        return deserializationProcessor.deserialize(clazz, reader);
    }

    @Override
    public <T> T validateFileAndParse(Class<T> clazz, File file) {
        var reader = Injector.provideFileInputReader(file);
        var context = MapperContextHolder.getContext();
        context.clearObjectGraph();

        deserializationFilterChain.filterInput(reader);
        deserializationFilterChain.filterClass(clazz);

        return deserializationProcessor.deserialize(clazz, reader);
    }

    @Override
    public String validateAndWriteToString(Object object) {
        var context = MapperContextHolder.getContext();
        context.clearObjectGraph();

        serializationFilterChain.invokeFilters(object);

        var writer = Injector.provideStringObjectWriter();
        serializationProcessor.serialize(object, writer);

        return writer.getWriter();
    }

    @Override
    public void validateAndWriteToStream(Object object, OutputStream stream) {
        var context = MapperContextHolder.getContext();
        context.clearObjectGraph();

        serializationFilterChain.invokeFilters(object);

        var writer = Injector.provideStreamObjectWriter(stream);
        serializationProcessor.serialize(object, writer);
    }

    @Override
    public void validateAndWriteToFile(Object object, File file) {
        var context = MapperContextHolder.getContext();
        context.clearObjectGraph();

        serializationFilterChain.invokeFilters(object);

        var writer = Injector.provideFileObjectWriter(file);
        serializationProcessor.serialize(object, writer);
    }
}
