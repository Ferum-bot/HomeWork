package com.github.ferum_bot.api.internal.serialization.delegates;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.DAGSerializationException;
import com.github.ferum_bot.api.internal.serialization.model.SerializationAliases;
import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.github.ferum_bot.api.enums.EntityType.*;

public class DeserializationDelegate {

    public Space deserialize(String string) {
        Space space;

        try {
            space = tryToDeserialize(string);
        } catch (DAGSerializationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DAGSerializationException(ex.getMessage());
        }

        if (space == null) {
            throw new DAGSerializationException();
        }
        return space;
    }

    private Space tryToDeserialize(String string) {
        return (Space) parseEntity(string);
    }

    private Coordinatable parseEntity(String stringEntity) {
        var typeAlias = SerializationAliases.TYPE_ALIAS + ": ";
        var coordinatesAlias = SerializationAliases.COORDINATES_ALIAS + ": ";

        var typeAliasIndex = stringEntity.indexOf(typeAlias);
        var coordinatesAliasIndex = stringEntity.indexOf(coordinatesAlias);

        var startTypeIndex = typeAliasIndex + typeAlias.length();
        var startCoordinatesIndex = coordinatesAliasIndex + coordinatesAlias.length();

        var type = parseType(stringEntity.substring(startTypeIndex));
        var coordinates = parseCoordinates(stringEntity.substring(startCoordinatesIndex));

        switch (type) {
            case SPACE -> {
                var space = ApiManager.spaceOf(coordinates);
                return parseSpaceChildren(space, stringEntity);
            }
            case ORIGIN -> {
                var origin = ApiManager.originOf(coordinates);
                return parseOriginChildren(origin, stringEntity);
            }
            case POINT -> {
                return ApiManager.pointOf(coordinates);
            }
        }

        throw new DAGSerializationException("Undefined entity type!");
    }

    private Space parseSpaceChildren(Space space, String stringEntity) {
        var childrenStartIndex = stringEntity.indexOf('[') + 1;
        var childrenEndIndex = stringEntity.lastIndexOf(']');
        var rawChildren = stringEntity.substring(childrenStartIndex, childrenEndIndex);
        var children = splitChildren(rawChildren);

        children.forEach(childString -> {
            var childEntity = parseEntity(childString);

            if (childEntity instanceof Point point) {
                space.addPoint(point);
            }
            if (childEntity instanceof Origin origin) {
                space.addOrigin(origin);
            }
            if (childEntity instanceof Space) {
                throw new DAGSerializationException("UnExpected space type!");
            }
        });

        return space;
    }

    private Origin parseOriginChildren(Origin origin, String stringEntity) {
        var childrenStartIndex = stringEntity.indexOf('[') + 1;
        var childrenEndIndex = stringEntity.lastIndexOf(']');
        var rawChildren = stringEntity.substring(childrenStartIndex, childrenEndIndex);
        var children = splitChildren(rawChildren);

        children.forEach(childString -> {
            var childEntity = parseEntity(childString);

            if (childEntity instanceof Point point) {
                origin.addPoint(point);
            }
            if (childEntity instanceof Origin anotherOrigin) {
                origin.addOrigin(anotherOrigin);
            }
            if (childEntity instanceof Space) {
                throw new DAGSerializationException("UnExpected space type!");
            }
        });

        return origin;
    }

    private Coord2D parseCoordinates(String coordinates) {
        var firstStartIndex = coordinates.indexOf('(') + 1;
        var firstEndIndex = coordinates.indexOf(',');
        var secondStartIndex = coordinates.indexOf(' ') + 1;
        var secondEndIndex = coordinates.indexOf(')');

        var xString = coordinates.substring(firstStartIndex, firstEndIndex);
        var yString = coordinates.substring(secondStartIndex, secondEndIndex);

        var x = Double.parseDouble(xString);
        var y = Double.parseDouble(yString);

        return new Coord2D(x, y);
    }

    private EntityType parseType(String type) {
        if (type.startsWith(SPACE.toString())) {
            return SPACE;
        }
        if (type.startsWith(ORIGIN.toString())) {
            return ORIGIN;
        }
        if (type.startsWith(POINT.toString())) {
            return POINT;
        }
        throw new DAGSerializationException("Undefined entity type: " + type);
    }

    private Collection<String> splitChildren(String rawChildren) {
        var children = new ArrayList<String>();
        var balance = 0;
        var lastOpenIndex = 0;
        var length = rawChildren.length();

        for (int i = 0; i < length; i++) {
            if (rawChildren.charAt(i) == '{') {
                balance++;
            }
            if (balance == 1) {
                lastOpenIndex = i;
            }
            if (rawChildren.charAt(i) == '}') {
                balance--;
            }
            if (rawChildren.charAt(i) == '}' && balance == 0) {
                children.add(rawChildren.substring(lastOpenIndex, i + 1));
            }
        }

        return children;
    }
}
