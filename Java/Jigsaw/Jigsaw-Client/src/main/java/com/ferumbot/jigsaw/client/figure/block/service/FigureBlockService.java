package com.ferumbot.jigsaw.client.figure.block.service;

import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.core.support.ValueThreadSequence;

public class FigureBlockService {

    public FigureBlockService() {
        ValueThreadSequence.flushToInitial();
    }

    public FigureBlock createWith(int x, int y) {
        var id = ValueThreadSequence.generateInt();
        return new FigureBlock(id, x, y);
    }
}
