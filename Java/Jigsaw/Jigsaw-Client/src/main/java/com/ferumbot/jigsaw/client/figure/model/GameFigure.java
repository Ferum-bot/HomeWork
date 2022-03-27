package com.ferumbot.jigsaw.client.figure.model;

import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.core.support.ValueThreadSequence;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class GameFigure {

    private final int id;

    private final List<FigureBlock> blocks;

    public GameFigure() {
        blocks = new LinkedList<>();
        id = ValueThreadSequence.generateInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameFigure that = (GameFigure) o;
        return id == that.id && blocks.equals(that.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blocks);
    }

    public int getId() {
        return id;
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
