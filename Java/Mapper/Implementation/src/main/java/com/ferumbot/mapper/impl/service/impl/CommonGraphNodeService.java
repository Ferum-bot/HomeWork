package com.ferumbot.mapper.impl.service.impl;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import ru.hse.homework4.DateFormat;
import ru.hse.homework4.Exported;
import ru.hse.homework4.PropertyName;
import ru.hse.homework4.enums.NullHandling;
import ru.hse.homework4.enums.UnknownPropertiesPolicy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class CommonGraphNodeService implements GraphNodeService {

    @Override
    public NullHandling getNullHandlingPolicy(GraphNode node) {
        var exported = getExportedAnnotation(node);

        if (exported.isEmpty()) {
            return NullHandling.EXCLUDE;
        } else {
            return exported.get().nullHandling();
        }
    }

    @Override
    public UnknownPropertiesPolicy getUnknownPropertiesPolice(GraphNode node) {
        var exported = getExportedAnnotation(node);

        if (exported.isEmpty()) {
            return UnknownPropertiesPolicy.FAIL;
        } else {
            return exported.get().unknownPropertiesPolicy();
        }
    }

    @Override
    public Collection<Field> getFields(GraphNode node) {
        var objectClass = node.objectClass();
        if (objectClass == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(objectClass.getDeclaredFields())
            .filter(field -> !field.isSynthetic())
            .filter(field -> !Modifier.isStatic(field.getModifiers()))
            .peek(Field::trySetAccessible)
            .toList();
    }

    @Override
    public Collection<Annotation> getFieldAnnotations(GraphNode node) {
        Field parentField;
        if (node.fieldInParent().isEmpty()) {
            return Collections.emptyList();
        } else {
            parentField = node.fieldInParent().get();
        }

        return Arrays.stream(parentField.getAnnotations()).toList();
    }

    @Override
    public Collection<Annotation> getClassAnnotations(GraphNode node) {
        var objectClass = node.objectClass();
        if (objectClass == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(objectClass.getAnnotations()).toList();
    }

    @Override
    public Collection<Constructor<?>> getConstructors(GraphNode node) {
        var objectClass = node.objectClass();
        if (objectClass == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(objectClass.getConstructors()).toList();
    }

    @Override
    public Optional<String> getNameAlias(GraphNode node) {
        var fieldAnnotations = getFieldAnnotations(node);
        var propertyName = fieldAnnotations.stream()
            .filter(annotation -> annotation.annotationType().equals(PropertyName.class))
            .map(annotation -> (PropertyName) annotation)
            .findFirst();

        if (propertyName.isPresent()) {
            return Optional.of(propertyName.get().value());
        }

        var fieldInParent = node.fieldInParent();
        if (fieldInParent.isEmpty()) {
            return Optional.empty();
        } else {
            var field = fieldInParent.get();
            return Optional.of(field.getName());
        }
    }

    @Override
    public Optional<String> getDateFormatValue(GraphNode node) {
        var fieldAnnotations = getFieldAnnotations(node);
        var dateFormat = fieldAnnotations.stream()
                .filter(annotation -> annotation.annotationType().equals(DateFormat.class))
                .map(annotation -> (DateFormat) annotation)
                .findFirst();

        if (dateFormat.isEmpty()) {
            return Optional.empty();
        } else {
            var format = dateFormat.get().value();
            return Optional.of(format);
        }
    }

    @Override
    public boolean parentClassIsCollection(GraphNode node) {
        var parentClass = node.parentClass();
        if (parentClass.isEmpty()) {
            return false;
        }
        return Collection.class.isAssignableFrom(parentClass.get());
    }

    private Optional<Exported> getExportedAnnotation(GraphNode node) {
        var objectClass = node.objectClass();
        var parentClassHolder = node.parentClass();

        if (objectClass == null && parentClassHolder.isEmpty()) {
            return Optional.empty();
        }
        if (objectClass == null) {
            var parentClass = parentClassHolder.get();
            return getExportedAnnotation(parentClass);
        }

        var objectExported = getExportedAnnotation(objectClass);
        if (objectExported.isPresent()) {
            return objectExported;
        }
        if (parentClassHolder.isEmpty()) {
            return Optional.empty();
        }

        var parentClass = parentClassHolder.get();
        return getExportedAnnotation(parentClass);
    }

    private Optional<Exported> getExportedAnnotation(Class<?> clazz) {
        var annotations = Arrays.stream(clazz.getAnnotations());
        return annotations
                .filter(annotation -> annotation.annotationType().equals(Exported.class))
                .map(annotation -> (Exported) annotation)
                .findFirst();
    }
}
