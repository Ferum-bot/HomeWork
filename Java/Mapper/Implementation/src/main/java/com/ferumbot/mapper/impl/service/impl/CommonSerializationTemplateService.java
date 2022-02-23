package com.ferumbot.mapper.impl.service.impl;

import com.ferumbot.mapper.impl.core.util.MapperConstants;
import com.ferumbot.mapper.impl.service.SerializationTemplatesService;

public class CommonSerializationTemplateService implements SerializationTemplatesService {

    @Override
    public String getNameAliasTemplate(String nameAlias) {
        return MapperConstants.NAME_BEGIN_SYMBOL +
                nameAlias +
                MapperConstants.NAME_END_SYMBOL +
                MapperConstants.SPACE +
                MapperConstants.NAME_SEPARATOR +
                MapperConstants.SPACE;
    }
}
