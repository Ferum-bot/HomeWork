package com.ferumbot.jigsaw.client.figure.model;

import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.core.support.ValueThreadSequence;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MutableGameFigure implements GameFigure {

    private final int id;

    private final List<FigureBlock> blocks;

    private Coordinates coordinates;

    public MutableGameFigure(List<FigureBlock> blocks) {
        this.blocks = blocks;
        id = ValueThreadSequence.generateInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableGameFigure that = (MutableGameFigure) o;
        return id == that.id && blocks.equals(that.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blocks);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isAddedToField() {
        return false;
    }

    @Override
    public Optional<Coordinates> getFieldCoordinates() {
        return Optional.ofNullable(coordinates);
    }

    @Override
    public List<FigureBlock> getFigureBlocks() {
        return Collections.unmodifiableList(blocks);
    }

    public Coordinates getRawFieldCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void addBlock(FigureBlock block) {
        blocks.add(block);
    }

    public boolean removeBlock(FigureBlock block) {
        return blocks.remove(block);
    }
}
