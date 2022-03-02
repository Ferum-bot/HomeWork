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

    @Override
    public String getNullNameAliasTemplate(String nameAlias) {
        var nameTemplate = getNameAliasTemplate(nameAlias);
        return nameTemplate + MapperConstants.UN_DEFINED_VALUE;
    }

    @Override
    public String getIdPropertyTemplate(Long id) {
        return MapperConstants.NAME_BEGIN_SYMBOL +
                MapperConstants.OBJECT_ID_ALIAS +
                MapperConstants.NAME_END_SYMBOL +
                MapperConstants.SPACE +
                MapperConstants.NAME_SEPARATOR +
                MapperConstants.SPACE +
                id;
    }
}
