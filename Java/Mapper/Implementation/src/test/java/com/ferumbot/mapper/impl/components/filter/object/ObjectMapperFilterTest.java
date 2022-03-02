package com.ferumbot.mapper.impl.components.filter.object;

import com.ferumbot.mapper.impl.components.filter.object.impl.ClassConstructorFilter;
import com.ferumbot.mapper.impl.components.filter.object.impl.DateTimeFormatFilter;
import com.ferumbot.mapper.impl.components.filter.object.impl.RetainCycleFilter;
import com.ferumbot.mapper.impl.components.filter.object.impl.SupportedClassFilter;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import com.ferumbot.mapper.impl.service.impl.CommonGraphNodeService;
import com.ferumbot.mapper.impl.service.impl.DefaultGraphBuildService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperFilterTest {

    private ObjectMapperFilter classConstructorFilter;
    private ObjectMapperFilter dateTimeFormatFilter;
    private ObjectMapperFilter retainCycleFilter;
    private ObjectMapperFilter supportedClassFilter;

    private ObjectGraphBuildService graphBuildService;
    private GraphNodeService graphNodeService;

    @BeforeEach
    void setUp() {
        graphBuildService = new DefaultGraphBuildService();
        graphNodeService = new CommonGraphNodeService();

        classConstructorFilter = new ClassConstructorFilter(graphBuildService, graphNodeService);
        dateTimeFormatFilter = new DateTimeFormatFilter(graphBuildService, graphNodeService);
        retainCycleFilter = new RetainCycleFilter(graphBuildService);
        supportedClassFilter = new SupportedClassFilter(graphBuildService);
    }

    @AfterEach
    void tearDown() {
        graphBuildService = null;
        graphNodeService = null;

        classConstructorFilter = null;
        dateTimeFormatFilter = null;
        retainCycleFilter = null;
        supportedClassFilter = null;
    }
}