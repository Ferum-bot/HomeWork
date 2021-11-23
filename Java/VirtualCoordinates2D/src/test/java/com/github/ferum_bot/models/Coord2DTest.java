package com.github.ferum_bot.models;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Coord2DTest {

    @Test
    public void ClassConstructor_empty_successCreating() {
        var xCoord = 1.0;
        var yCoord = 1.0;

        var actualCoord2d = new Coord2D(xCoord, yCoord);

        Assertions.assertEquals(xCoord, actualCoord2d.x());
        Assertions.assertEquals(yCoord, actualCoord2d.y());
    }
}