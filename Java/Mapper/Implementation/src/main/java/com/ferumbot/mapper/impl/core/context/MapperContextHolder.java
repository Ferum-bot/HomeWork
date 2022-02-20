package com.ferumbot.mapper.impl.core.context;

import java.util.Optional;

public class MapperContextHolder {

    private static final ThreadLocal<Optional<MapperContext>> threadLocalContext;

    static {
        threadLocalContext = ThreadLocal.withInitial(Optional::empty);
    }

    public static MapperContext getContext() {
        var currentContext = threadLocalContext.get();
        if (currentContext.isEmpty()) {
            var context = new DefaultMapperContext();
            threadLocalContext.set(Optional.of(context));
        }

        return threadLocalContext.get().get();
    }
}
