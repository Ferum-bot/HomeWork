package com.ferumbot.mapper.impl.interactor.impl;

import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;
import com.ferumbot.mapper.impl.core.context.MapperContextHolder;
import com.ferumbot.mapper.impl.core.models.MappingSettings;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.interactor.MapperInteractor;

import java.io.*;

public class DefaultMapperInteractor implements MapperInteractor {

    private final SerializationFilterChain serializationFilterChain;

    public DefaultMapperInteractor(MappingSettings settings) {
        var context = MapperContextHolder.getContext();
        context.setSettings(settings);

        serializationFilterChain = Injector.provideSerializationFilterChain();
    }

    @Override
    public <T> T validateStringAndParse(Class<T> clazz, String input) {
        return null;
    }

    @Override
    public <T> T validateStreamAndParse(Class<T> clazz, InputStream stream) throws IOException {
        return null;
    }

    @Override
    public <T> T validateFileAndParse(Class<T> clazz, File file) throws IOException {
        return null;
    }

    @Override
    public String validateAndWriteToString(Object object) {
        serializationFilterChain.invokeFilters(object);

        return null;
    }

    @Override
    public void validateAndWriteToStream(Object object, OutputStream stream) throws IOException {
        serializationFilterChain.invokeFilters(object);

    }

    @Override
    public void validateAndWriteToFile(Object object, File file) throws IOException {
        serializationFilterChain.invokeFilters(object);

    }
}
