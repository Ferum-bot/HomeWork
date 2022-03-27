package com.ferumbot.jigsaw.client.figure.block.model;

import com.ferumbot.jigsaw.client.field.models.FieldBlock;
import com.ferumbot.jigsaw.core.support.ValueThreadSequence;

public record FigureBlock(
    int identifier,
    int xCoordinate,
    int yCoordinate
) {

    public boolean equalsTo(FieldBlock fieldBlock) {
        return xCoordinate == fieldBlock.xCoordinate() && yCoordinate == fieldBlock.yCoordinate();
    }
}