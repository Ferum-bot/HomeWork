package com.ferumbot.jigsaw.client.field.service;

import com.ferumbot.jigsaw.client.field.models.FieldBlock;
import com.ferumbot.jigsaw.client.field.models.MutableGameField;
import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.client.figure.model.MutableGameFigure;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameFieldService {

    public boolean canAddNewFigure(MutableGameField field, MutableGameFigure figure, Coordinates targetCoordinate) {
        var params = field.getParams();
        var figureBlocks = figure.getFigureBlocks();
        var fieldBlocks = generateFieldBlocks(field);

        System.out.println("Target Coordinates: " + targetCoordinate);

        for (FigureBlock figureBlock: figureBlocks) {
            var absoluteX = figureBlock.xCoordinate() + targetCoordinate.xCoordinate();
            var absoluteY = figureBlock.yCoordinate() + targetCoordinate.yCoordinate();
            System.out.println("Absolute Block: " + absoluteX + " " + absoluteY);

            if (absoluteX < 0 || absoluteX >= params.blockWidth()) {
                return false;
            }
            if (absoluteY < 0 || absoluteY >= params.blockHeight()) {
                return false;
            }

            if (fieldContainsBlock(fieldBlocks, figureBlock, targetCoordinate)) {
                return false;
            }
        }

        return true;
    }

    public List<FieldBlock> generateFieldBlocks(MutableGameField field) {
        var figures = field.getMutableFigures();
        var fieldBlocks = new ArrayList<FieldBlock>();
        var fieldParams = field.getParams();
        boolean[][] usedCoordinates = new boolean[fieldParams.blockWidth()][fieldParams.blockHeight()];

        figures.forEach(figure -> {
            var absoluteCoordinates = figure.getRawFieldCoordinates();
            var blocks = figure.getFigureBlocks();

            blocks.forEach(block -> {
                var relativeX = block.xCoordinate();
                var relativeY = block.yCoordinate();
                var absoluteX = relativeX + absoluteCoordinates.xCoordinate();
                var absoluteY = relativeY + absoluteCoordinates.yCoordinate();

                var fieldBlock = new FieldBlock(
                    absoluteX, absoluteY, false,
                    Optional.of(figure), Optional.of(block)
                );
                fieldBlocks.add(fieldBlock);
                usedCoordinates[absoluteX][absoluteY] = true;
            });
        });

        for (int xCoordinate = 0; xCoordinate < fieldParams.blockWidth(); xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < fieldParams.blockHeight(); yCoordinate++) {
                if (usedCoordinates[xCoordinate][yCoordinate]) {
                    continue;
                }

                var block = new FieldBlock(
                    xCoordinate, yCoordinate, true,
                    Optional.empty(), Optional.empty()
                );
                fieldBlocks.add(block);
            }
        }

        return fieldBlocks;
    }

    private boolean fieldContainsBlock(List<FieldBlock> fieldBlocks, FigureBlock figureBlock, Coordinates targetCoordinates) {
        var absoluteX = figureBlock.xCoordinate() + targetCoordinates.xCoordinate();
        var absoluteY = figureBlock.yCoordinate() + targetCoordinates.yCoordinate();

        for (FieldBlock fieldBlock: fieldBlocks) {
            if (fieldBlock.isEmpty()) {
                continue;
            }
            System.out.println("FieldBlock: " + fieldBlock.xCoordinate() + " " + fieldBlock.yCoordinate());
            if (fieldBlock.xCoordinate() == absoluteX && fieldBlock.yCoordinate() == absoluteY) {
                return true;
            }
        }

        return false;
    }
}
