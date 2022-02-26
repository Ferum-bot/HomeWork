package com.ferumbot.mapper.impl.di;

import com.ferumbot.mapper.impl.components.filter.DeserializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.input.InputMapperFilter;
import com.ferumbot.mapper.impl.components.filter.input.impl.InputStructureFilter;
import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.components.filter.object.impl.ClassConstructorFilter;
import com.ferumbot.mapper.impl.components.filter.object.impl.DateTimeFormatFilter;
import com.ferumbot.mapper.impl.components.filter.object.impl.RetainCycleFilter;
import com.ferumbot.mapper.impl.components.filter.object.impl.SupportedClassFilter;
import com.ferumbot.mapper.impl.components.inputreader.InputReader;
import com.ferumbot.mapper.impl.components.inputreader.impl.FileInputReader;
import com.ferumbot.mapper.impl.components.inputreader.impl.InputStreamInputReader;
import com.ferumbot.mapper.impl.components.inputreader.impl.StringInputReader;
import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.FileObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.OutputStreamObjectWriter;
import com.ferumbot.mapper.impl.components.objectwriter.impl.StringObjectWriter;
import com.ferumbot.mapper.impl.components.visitor.ManagedVisitor;
import com.ferumbot.mapper.impl.components.visitor.SimpleVisitor;
import com.ferumbot.mapper.impl.components.visitor.impl.DFSManagedVisitor;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.core.models.MappingSettings;
import com.ferumbot.mapper.impl.interactor.MapperInteractor;
import com.ferumbot.mapper.impl.interactor.impl.DefaultMapperInteractor;
import com.ferumbot.mapper.impl.processor.DeserializationProcessor;
import com.ferumbot.mapper.impl.processor.SerializationProcessor;
import com.ferumbot.mapper.impl.service.DateTimeService;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import com.ferumbot.mapper.impl.service.SerializationTemplatesService;
import com.ferumbot.mapper.impl.service.impl.CommonGraphNodeService;
import com.ferumbot.mapper.impl.service.impl.CommonSerializationTemplateService;
import com.ferumbot.mapper.impl.service.impl.DefaultDateTimeService;
import com.ferumbot.mapper.impl.service.impl.DefaultGraphBuildService;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class Injector {

    public static MapperInteractor provideInteractor(MappingSettings settings) {
        return new DefaultMapperInteractor(settings);
    }

    public static SerializationFilterChain provideSerializationFilterChain() {
        var filterChain = new SerializationFilterChain();

        filterChain.addFilter(provideRetainCycleFilter());
        filterChain.addFilter(provideSupportedClassFilter());
        filterChain.addFilter(provideClassConstructorFilter());
        filterChain.addFilter(provideDateTimeFilter());

        return filterChain;
    }

    public static DeserializationFilterChain provideDeserializationFilterChain() {
        var filterChain = new DeserializationFilterChain();

        filterChain.addObjectFilter(provideSupportedClassFilter());
        filterChain.addObjectFilter(provideClassConstructorFilter());
        filterChain.addObjectFilter(provideDateTimeFilter());

        filterChain.addInputFilter(provideInputStructureFilter());

        return filterChain;
    }

    public static ObjectMapperFilter provideDateTimeFilter() {
        var graphBuildService = provideGraphBuildService();
        var graphNodeService = provideGraphNodeService();
        return new DateTimeFormatFilter(graphBuildService, graphNodeService);
    }

    public static ObjectMapperFilter provideRetainCycleFilter() {
        var graphBuildService = provideGraphBuildService();
        return new RetainCycleFilter(graphBuildService);
    }

    public static ObjectMapperFilter provideSupportedClassFilter() {
        var graphBuildService = provideGraphBuildService();
        return new SupportedClassFilter(graphBuildService);
    }

    public static ObjectMapperFilter provideClassConstructorFilter() {
        var graphBuildService = provideGraphBuildService();
        var graphNodeService = provideGraphNodeService();
        return new ClassConstructorFilter(graphBuildService, graphNodeService);
    }

    public static InputMapperFilter provideInputStructureFilter() {
        return new InputStructureFilter();
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

    public static InputReader<String> provideStringInputReader(String input) {
        return new StringInputReader(input);
    }

    public static InputReader<File> provideFileInputReader(File input) {
        return new FileInputReader(input);
    }

    public static InputReader<InputStream> provideStreamInputReader(InputStream input) {
        return new InputStreamInputReader(input);
    }

    public static SerializationProcessor provideSerializationProcessor() {
        var graphBuildService = provideGraphBuildService();
        var graphNodeService = provideGraphNodeService();
        var serializationTemplateService = provideSerializationTemplateService();
        var dateTimeService = provideDateTimeService();
        return new SerializationProcessor(
            graphBuildService, graphNodeService, serializationTemplateService, dateTimeService
        );
    }

    public static DeserializationProcessor provideDeserializationProcessor() {
        return new DeserializationProcessor();
    }

    public static ObjectGraphBuildService provideGraphBuildService() {
        return new DefaultGraphBuildService();
    }

    public static GraphNodeService provideGraphNodeService() {
        return new CommonGraphNodeService();
    }

    public static SerializationTemplatesService provideSerializationTemplateService() {
        return new CommonSerializationTemplateService();
    }

    public static DateTimeService provideDateTimeService() {
        return new DefaultDateTimeService();
    }

    public static SimpleVisitor provideSimpleVisitor(GraphNode rootNode) {
        return new DFSManagedVisitor(rootNode);
    }

    public static ManagedVisitor provideManagedVisitor(GraphNode rootNode) {
        return new DFSManagedVisitor(rootNode);
    }
}