package com.ferumbot.mapper.impl.di;

import com.ferumbot.mapper.impl.components.filter.DeserializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.impl.DateTimeFormatFilter;
import com.ferumbot.mapper.impl.components.filter.impl.RetainCycleFilter;
import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.FileObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.OutputStreamObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.StringObjectWriter;
import com.ferumbot.mapper.impl.core.models.MappingSettings;
import com.ferumbot.mapper.impl.interactor.MapperInteractor;
import com.ferumbot.mapper.impl.interactor.impl.DefaultMapperInteractor;
import com.ferumbot.mapper.impl.processor.DeserializationProcessor;
import com.ferumbot.mapper.impl.processor.SerializationProcessor;

import java.io.File;
import java.io.OutputStream;

public class Injector {

    public static MapperInteractor provideInteractor(MappingSettings settings) {
        return new DefaultMapperInteractor(settings);
    }

    public static SerializationFilterChain provideSerializationFilterChain() {
        var filterChain = new SerializationFilterChain();

        filterChain.addFilter(new DateTimeFormatFilter());
        filterChain.addFilter(new RetainCycleFilter());

        return filterChain;
    }

    public static DeserializationFilterChain provideDeserializationFilterChain() {

    }

    public static ObjectWriter<String> provideStringObjectWriter() {
        return new StringObjectWriter();
    }

    public static ObjectWriter<OutputStream> provideStreamObjectWriter(OutputStream stream) {
        return new OutputStreamObjectWriter(stream);
    }

    public static ObjectWriter<File> provideFileObjectWriter(File file) {
        return new FileObjectWriter(file);
    }

    public static SerializationProcessor provideSerializationProcessor() {
        return new SerializationProcessor();
    }

    public static DeserializationProcessor provideDeserializationProcessor() {
        return new DeserializationProcessor();
    }
}
