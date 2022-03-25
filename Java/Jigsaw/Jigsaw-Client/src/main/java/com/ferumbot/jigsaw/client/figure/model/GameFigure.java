package com.ferumbot.jigsaw.client.figure.model;

import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameFigure {

    private final List<FigureBlock> blocks;

    public GameFigure() {
        blocks = new LinkedList<>();
    }

    public void addBlock(FigureBlock block) {
        blocks.add(block);
    }

    public boolean removeBlock(FigureBlock block) {
        return blocks.remove(block);
    }

    public List<FigureBlock> getBlocks() {
        return Collections.unmodifiableList(blocks);
    }
}
