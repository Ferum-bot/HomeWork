package com.ferumbot.mapper.impl;

import com.ferumbot.mapper.impl.core.models.MappingSettings;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.interactor.MapperInteractor;
import ru.hse.homework4.Mapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DefaultMapper implements Mapper {

    private final MapperInteractor interactor;

    public DefaultMapper() {
        this(false);
    }

    public DefaultMapper(boolean retainIdentity) {
        var settings = new MappingSettings(retainIdentity);

        interactor = Injector.provideInteractor(settings);
    }

    @Override
    public <T> T readFromString(Class<T> clazz, String input) {
        return interactor.validateStringAndParse(clazz, input);
    }

    @Override
    public <T> T read(Class<T> clazz, InputStream stream) throws IOException {
        return interactor.validateStreamAndParse(clazz, stream);
    }

    @Override
    public <T> T read(Class<T> clazz, File file) throws IOException {
        return interactor.validateFileAndParse(clazz, file);
    }

    @Override
    public String writeToString(Object object) {
        return interactor.validateAndWriteToString(object);
    }

    @Override
    public void write(Object object, OutputStream stream) throws IOException {
        interactor.validateAndWriteToStream(object, stream);
    }

    @Override
    public void write(Object object, File file) throws IOException {
        interactor.validateAndWriteToFile(object, file);
    }
}
