package com.ferumbot.jigsaw.client.field.models;

import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;

import java.util.Optional;

public record FieldBlock(
    int xCoordinate,
    int yCoordinate,
    boolean isEmpty,

    Optional<GameFigure> figureReference,
    Optional<FigureBlock> blockReference
) { }
