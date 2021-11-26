package com.github.ferum_bot.api.internal.serialization.model;

public enum SerializationAliases {
    TYPE_ALIAS("type"),
    COORDINATES_ALIAS("coordinates"),
    CHILDREN_ALIAS("children");

    private final String alias;

    SerializationAliases(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
