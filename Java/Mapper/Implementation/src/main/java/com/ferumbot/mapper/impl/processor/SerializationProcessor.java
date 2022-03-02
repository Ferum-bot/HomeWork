package com.ferumbot.mapper.impl.processor;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.core.util.MapperConstants;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.service.DateTimeService;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import com.ferumbot.mapper.impl.service.SerializationTemplatesService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.ferumbot.mapper.impl.core.enums.ObjectType.*;
import static ru.hse.homework4.enums.NullHandling.INCLUDE;

@SuppressWarnings("ClassCanBeRecord")
public class SerializationProcessor {

    private final ObjectGraphBuildService graphBuildService;

    private final GraphNodeService graphNodeService;

    private final SerializationTemplatesService serializationTemplatesService;

    private final DateTimeService dateTimeService;

    public SerializationProcessor(
        ObjectGraphBuildService graphBuildService,
        GraphNodeService graphNodeService,
        SerializationTemplatesService serializationTemplatesService,
        DateTimeService dateTimeService
    ) {
        this.graphBuildService = graphBuildService;
        this.graphNodeService = graphNodeService;
        this.serializationTemplatesService = serializationTemplatesService;
        this.dateTimeService = dateTimeService;
    }

    public void serialize(Object object, ObjectWriter<?> objectWriter) {
        var graph = graphBuildService.getFromContextOrBuild(object);
        var visitor = Injector.provideManagedVisitor(graph);

        visitor.visit(
            graphNode -> beforeVisit(graphNode, objectWriter),
            graphNode -> onVisit(graphNode, objectWriter),
            graphNode -> afterVisit(graphNode, objectWriter)
        );
    }

    private boolean beforeVisit(GraphNode graphNode, ObjectWriter<?> writer) {
        var type = graphNode.type();
        return type != UN_SUPPORTED;
    }

    private void onVisit(GraphNode graphNode, ObjectWriter<?> writer) {
        var type = graphNode.type();

        if (type == UN_SUPPORTED) {
            return;
        }

        writeNameAlias(graphNode, writer);

        switch (type) {
            case EXPORTED_CLASS -> {
                var id = graphNode.id();
                var idPropertyAlias = serializationTemplatesService.getIdPropertyTemplate(id);

                writer.writeToEnd(MapperConstants.OBJECT_BEGIN_SYMBOL);
                writer.writeToEnd(MapperConstants.NEW_LINE);
                writer.writeToEnd(idPropertyAlias);
            }
            case LIST_COLLECTION, SET_COLLECTION -> {
                writer.writeToEnd(MapperConstants.COLLECTION_BEGIN_SYMBOL);
            }
            case LOCAL_DATE -> {
                var value = (LocalDate) graphNode.object();
                var pattern = getDateTimePattern(graphNode);
                var valueString = dateTimeService.convertToString(value, pattern);

                writer.writeToEnd(MapperConstants.NAME_BEGIN_SYMBOL);
                writer.writeToEnd(valueString);
                writer.writeToEnd(MapperConstants.NAME_END_SYMBOL);
            }
            case LOCAL_TIME -> {
                var value = (LocalTime) graphNode.object();
                var pattern = getDateTimePattern(graphNode);
                var valueString = dateTimeService.convertToString(value, pattern);

                writer.writeToEnd(MapperConstants.NAME_BEGIN_SYMBOL);
                writer.writeToEnd(valueString);
                writer.writeToEnd(MapperConstants.NAME_END_SYMBOL);
            }
            case LOCAL_DATE_TIME -> {
                var value = (LocalDateTime) graphNode.object();
                var pattern = getDateTimePattern(graphNode);
                var valueString = dateTimeService.convertToString(value, pattern);

                writer.writeToEnd(MapperConstants.NAME_BEGIN_SYMBOL);
                writer.writeToEnd(valueString);
                writer.writeToEnd(MapperConstants.NAME_END_SYMBOL);
            }
            case STRING -> {
                var value = (String) graphNode.object();

                writer.writeToEnd(MapperConstants.NAME_BEGIN_SYMBOL);
                writer.writeToEnd(value);
                writer.writeToEnd(MapperConstants.NAME_END_SYMBOL);
            }
            case PRIMITIVE -> {
                var value = String.valueOf(graphNode.object());

                writer.writeToEnd(value);
            }
            case ENUM_CLASS -> {
                var value = graphNode.object().toString();

                writer.writeToEnd(value);
            }
        }
        writer.writeToEnd(MapperConstants.NEW_LINE);
    }

    private void afterVisit(GraphNode graphNode, ObjectWriter<?> writer) {
        var type = graphNode.type();

        if (type == EXPORTED_CLASS) {
            writer.writeToEnd(MapperConstants.OBJECT_END_SYMBOL);
            writer.writeToEnd(MapperConstants.NEW_LINE);
        }
        if (type == LIST_COLLECTION || type == SET_COLLECTION) {
            writer.writeToEnd(MapperConstants.COLLECTION_END_SYMBOL);
            writer.writeToEnd(MapperConstants.NEW_LINE);
        }
    }

    private void writeNameAlias(GraphNode node, ObjectWriter<?> writer) {
        if (graphNodeService.parentClassIsCollection(node)) {
            return;
        }

        var object = node.object();
        var nameAlias = graphNodeService.getNameAlias(node);
        var nullHandling = graphNodeService.getNullHandlingPolicy(node);

        if (nameAlias.isEmpty()) {
            return;
        }
        if (object != null) {
            var nameAliasTemplate = serializationTemplatesService.getNameAliasTemplate(nameAlias.get());
            writer.writeToEnd(nameAliasTemplate);
            return;
        }
        if (nullHandling == INCLUDE) {
            var nameAliasTemplate = serializationTemplatesService.getNullNameAliasTemplate(nameAlias.get());
            writer.writeToEnd(nameAliasTemplate);
        }
    }

    private String getDateTimePattern(GraphNode node) {
        var type = node.type();
        var format = graphNodeService.getDateFormatValue(node);

        switch (type) {
            case LOCAL_DATE -> {
                return format.orElse(MapperConstants.LOCAL_DATE_PATTERN);
            }
            case LOCAL_TIME -> {
                return format.orElse(MapperConstants.LOCAL_TIME_PATTERN);
            }
            case LOCAL_DATE_TIME -> {
                return format.orElse(MapperConstants.LOCAL_DATE_TIME_PATTERN);
            }
        }

        return "";
    }
}
