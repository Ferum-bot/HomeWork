package com.ferumbot.jigsaw.client.figure.service;

import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.client.figure.block.service.FigureBlockService;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;

import java.util.LinkedList;
import java.util.List;

public class GameFigureGenerator {

    private final FigureBlockService figureBlockService = new FigureBlockService();

    public GameFigure generateRandomFigure() {

    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure1Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 0), blockOf(1, 1),
            blockOf(1, 2), blockOf(2, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure1Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0), blockOf(0, 1)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure1Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(1, 1), blockOf(1, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure1Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(2, 1), blockOf(2, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure2Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 0), blockOf(1, 1),
            blockOf(1, 2), blockOf(0, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure2Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(2, 1), blockOf(2, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure2Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(0, 0), blockOf(1, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure2Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(0, 1),
            blockOf(1, 1), blockOf(2, 1)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure3Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(1, 1), blockOf(1, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure3Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(1, 1), blockOf(2, 1)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure3Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(0, 1),
            blockOf(1, 1), blockOf(1, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure3Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(1, 0), blockOf(2, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure4Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0), blockOf(2, 1),
            blockOf(2, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure4Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure4Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(0, 1),
            blockOf(0, 2), blockOf(1, 2),
            blockOf(2, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure4Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(1, 2),
            blockOf(2, 2), blockOf(2, 1),
            blockOf(2, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure5Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0), blockOf(1, 1),
            blockOf(1, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure5Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(1, 2),
            blockOf(2, 2), blockOf(1, 1),
            blockOf(1, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure5Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(0, 0), blockOf(1, 1),
            blockOf(2, 1)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure5Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(2, 1), blockOf(2, 2),
            blockOf(2, 0)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure6Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1), blockOf(2, 1)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure6Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 0), blockOf(1, 1), blockOf(1, 2)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure7() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 1)
        );
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure8Position1() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure8Position2() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure8Position3() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure8Position4() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure9Position1() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure9Position2() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure9Position3() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    /**
     *
     * @return
     */
    public GameFigure generateFigure9Position4() {
        List<FigureBlock> blocks = List.of();
        return new GameFigure(blocks);
    }

    private FigureBlock blockOf(int x, int y) {
        return figureBlockService.createWith(x, y);
    }
}
