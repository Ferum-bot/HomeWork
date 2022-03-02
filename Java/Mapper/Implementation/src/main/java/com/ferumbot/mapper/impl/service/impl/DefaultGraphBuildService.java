package com.ferumbot.mapper.impl.service.impl;

import com.ferumbot.mapper.impl.core.context.MapperContextHolder;
import com.ferumbot.mapper.impl.core.enums.ObjectType;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.core.util.IdentifierProvider;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import ru.hse.homework4.Exported;
import ru.hse.homework4.Ignored;
import ru.hse.homework4.exceptions.MapperReflectException;
import ru.hse.homework4.exceptions.RetainCycleException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class DefaultGraphBuildService implements ObjectGraphBuildService {

    private enum ObjectColor {
        GRAY, BLACK;
    }

    private final Map<Object, Long> graphIds = new IdentityHashMap<>();

    private final Map<Object, ObjectColor> objectsColor = new IdentityHashMap<>();

    @Override
    public GraphNode getFromContextOrBuild(Class<?> clazz) {
        var context = MapperContextHolder.getContext();
        var graphHolder = context.getObjectGraph();

        if (graphHolder.isEmpty())  {
            var graph = buildGraphFrom(clazz);
            context.setObjectGraph(graph);
            return graph;
        } else {
            return graphHolder.get();
        }
    }

    @Override
    public GraphNode getFromContextOrBuild(Object object) {
        var context = MapperContextHolder.getContext();
        var graphHolder = context.getObjectGraph();

        if (graphHolder.isPresent()) {
            return graphHolder.get();
        } else {
            var graph = buildGraphFrom(object);
            context.setObjectGraph(graph);
            return graph;
        }
    }

    @Override
    public GraphNode buildGraphFrom(Class<?> clazz) {
        graphIds.clear();
        try {
            return buildNode(Optional.empty(), Optional.empty(), clazz);
        } catch (IllegalAccessException exception) {
            var message = "Can't access class field. " + exception.getMessage();
            throw new MapperReflectException(message);
        }
    }

    @Override
    public GraphNode buildGraphFrom(Object object) {
        graphIds.clear();
        try {
            return buildNode(Optional.empty(), Optional.empty(), object);
        } catch (IllegalAccessException exception) {
            var message = "Can't access class field. " + exception.getMessage();
            throw new MapperReflectException(message);
        }
    }

    private GraphNode buildNode(
        Optional<Field> fieldInParent, Optional<Class<?>> parentClass, Class<?> objectClass
    ) throws IllegalAccessException {
        var type = getObjectType(objectClass);
        Collection<GraphNode> children = Collections.emptyList();

        if (type == ObjectType.EXPORTED_CLASS) {
            children = getExportedChildren(objectClass);
        }
        if (type == ObjectType.LIST_COLLECTION || type == ObjectType.SET_COLLECTION) {
            children = getCollectionChildren(fieldInParent);
        }

        return new GraphNode(
            null, objectClass, null, fieldInParent,
            parentClass, type, children
        );
    }

    private GraphNode buildNode(
        Optional<Field> fieldInParent, Optional<Class<?>> parentClass, Object object
    ) throws IllegalAccessException {
        if (object == null) {
            return GraphNode.ofNullObject(fieldInParent, parentClass);
        }

        var objectClass = object.getClass();
        var type = getObjectType(objectClass);
        var id = getNodeId(object);
        Collection<GraphNode> children = Collections.emptyList();

        checkForRetainCycleOrThrow(object);

        if (type == ObjectType.EXPORTED_CLASS) {
            children = getExportedChildren(object);
        }
        if (type == ObjectType.LIST_COLLECTION || type == ObjectType.SET_COLLECTION) {
            children = getCollectionChildren(object, fieldInParent);
        }

        objectsColor.put(object, ObjectColor.BLACK);
        return new GraphNode(
            id, objectClass, object, fieldInParent,
            parentClass, type, children
        );
    }

    private ObjectType getObjectType(Class<?> clazz) {
        if (Set.class.isAssignableFrom(clazz)) {
            return ObjectType.SET_COLLECTION;
        }
        if (List.class.isAssignableFrom(clazz)) {
            return ObjectType.LIST_COLLECTION;
        }

        if (clazz.equals(String.class)) {
            return ObjectType.STRING;
        }
        if (clazz.equals(LocalDate.class)) {
            return ObjectType.LOCAL_DATE;
        }
        if (clazz.equals(LocalTime.class)) {
            return ObjectType.LOCAL_TIME;
        }
        if (clazz.equals(LocalDateTime.class)) {
            return ObjectType.LOCAL_DATE_TIME;
        }

        if (clazz.isEnum()) {
            return ObjectType.ENUM_CLASS;
        }

        if (isExportedClass(clazz)) {
            return ObjectType.EXPORTED_CLASS;
        }
        if (isPrimitive(clazz)) {
            return ObjectType.PRIMITIVE;
        }

        return ObjectType.UN_SUPPORTED;
    }

    private Collection<GraphNode> getExportedChildren(Object object) throws IllegalAccessException {
        var objectClass = object.getClass();
        var fields = getFilteredFields(objectClass);

        Collection<GraphNode> resultNodes = new ArrayList<>();
        for (Field field: fields) {
            if (isIgnoredField(field)) {
                continue;
            }

            var fieldObject = field.get(object);
            var node = buildNode(Optional.of(field), Optional.of(objectClass), fieldObject);
            resultNodes.add(node);
        }

        return resultNodes;
    }

    private Collection<GraphNode> getExportedChildren(Class<?> objectClass) throws IllegalAccessException {
        var fields = getFilteredFields(objectClass);

        Collection<GraphNode> resultNodes = new ArrayList<>();
        for (Field field: fields) {
            if (isIgnoredField(field)) {
                continue;
            }

            var fieldClass = field.getType();
            resultNodes.add(buildNode(Optional.of(field), Optional.of(objectClass), fieldClass));
        }

        return resultNodes;
    }

    private Collection<GraphNode> getCollectionChildren(
        Object object, Optional<Field> fieldInParent
    ) throws IllegalAccessException {
        Optional<Class<?>> parentClass = Optional.of(Collection.class);
        var collection = (Collection<?>) object;

        Collection<GraphNode> resultNodes = new ArrayList<>();
        for (Object element: collection) {
            var node = buildNode(fieldInParent, parentClass, element);
            resultNodes.add(node);
        }

        return resultNodes;
    }

    private Collection<GraphNode> getCollectionChildren(
        Optional<Field> fieldInParent
    ) throws IllegalAccessException {
        Field parentField;
        if (fieldInParent.isEmpty()) {
            return Collections.emptyList();
        } else {
            parentField = fieldInParent.get();
        }

        Optional<Class<?>> parentClass = Optional.of(Collection.class);
        var type = (ParameterizedType) parentField.getGenericType();
        var typeClass = type.getActualTypeArguments()[0];

        return List.of(buildNode(fieldInParent, parentClass, typeClass));
    }

    private Collection<Field> getFilteredFields(Class<?> objectClass) {
        return Arrays.stream(objectClass.getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .peek(Field::trySetAccessible)
                .toList();
    }

    private boolean isExportedClass(Class<?> clazz) {
        return Arrays.stream(clazz.getAnnotations())
            .anyMatch(annotation -> annotation.annotationType().equals(Exported.class));
    }

    private boolean isPrimitive(Class<?> clazz) {
        if (clazz == Byte.TYPE || clazz == Byte.class) {
            return true;
        }
        if (clazz == Short.TYPE || clazz == Short.class) {
            return true;
        }
        if (clazz == Integer.TYPE || clazz == Integer.class) {
            return true;
        }
        if (clazz == Long.TYPE || clazz == Long.class) {
            return true;
        }
        if (clazz == Float.TYPE || clazz == Float.class) {
            return true;
        }
        if (clazz == Double.TYPE || clazz == Double.class) {
            return true;
        }
        if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            return true;
        }
        if (clazz == Character.TYPE || clazz == Character.class) {
            return true;
        }

        return false;
    }

    private boolean isIgnoredField(Field field) {
        var annotations = field.getAnnotations();
        return Arrays.stream(annotations)
            .anyMatch(annotation -> annotation.annotationType().equals(Ignored.class));
    }

    private Long getNodeId(Object object) {
        if (graphIds.containsKey(object)) {
            return graphIds.get(object);
        }
        var id = IdentifierProvider.nextId();
        graphIds.put(object, id);
        return id;
    }

    private void checkForRetainCycleOrThrow(Object object) {
        if (objectsColor.containsKey(object)) {
            var currentColor = objectsColor.get(object);
            if (currentColor == ObjectColor.GRAY) {
                var message = "Detected retain cycle in object: " + object.toString();
                throw new RetainCycleException(message);
            }
        } else {
            objectsColor.put(object, ObjectColor.GRAY);
        }
    }
}
