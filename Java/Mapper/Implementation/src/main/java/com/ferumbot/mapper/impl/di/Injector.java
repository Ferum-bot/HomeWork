package com.ferumbot.mapper.impl.di;

import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.impl.DateTimeFormatFilter;
import com.ferumbot.mapper.impl.components.filter.impl.RetainCycleFilter;
import com.ferumbot.mapper.impl.core.models.MappingSettings;
import com.ferumbot.mapper.impl.interactor.MapperInteractor;
import com.ferumbot.mapper.impl.interactor.impl.DefaultMapperInteractor;

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
}
