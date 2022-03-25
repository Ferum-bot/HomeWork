package com.ferumbot.jigsaw.client.figure.block.model;

import com.ferumbot.jigsaw.core.support.ValueThreadSequence;

public record FigureBlock(
    int identifier,
    int xCoordinate,
    int yCoordinate
) { }