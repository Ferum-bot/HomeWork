package com.ferumbot.jigsaw.client.figure.service;

import com.ferumbot.jigsaw.client.figure.block.model.FigureBlock;
import com.ferumbot.jigsaw.client.figure.block.service.FigureBlockService;
import com.ferumbot.jigsaw.client.figure.model.MutableGameFigure;
import com.ferumbot.jigsaw.core.support.RandomValueGenerator;

import java.util.LinkedList;
import java.util.List;

public class GameFigureGenerator {

    private final FigureBlockService figureBlockService = new FigureBlockService();

    private final List<MutableGameFigure> allFigures = new LinkedList<>();

    public GameFigureGenerator() {
        allFigures.add(generateFigure1Position1());
        allFigures.add(generateFigure1Position2());
        allFigures.add(generateFigure1Position3());
        allFigures.add(generateFigure1Position4());

        allFigures.add(generateFigure2Position1());
        allFigures.add(generateFigure2Position2());
        allFigures.add(generateFigure2Position3());
        allFigures.add(generateFigure2Position4());

        allFigures.add(generateFigure3Position1());
        allFigures.add(generateFigure3Position2());
        allFigures.add(generateFigure3Position3());
        allFigures.add(generateFigure3Position4());

        allFigures.add(generateFigure4Position1());
        allFigures.add(generateFigure4Position2());
        allFigures.add(generateFigure4Position3());
        allFigures.add(generateFigure4Position4());

        allFigures.add(generateFigure5Position1());
        allFigures.add(generateFigure5Position2());
        allFigures.add(generateFigure5Position3());
        allFigures.add(generateFigure5Position4());

        allFigures.add(generateFigure6Position1());
        allFigures.add(generateFigure6Position2());

        allFigures.add(generateFigure7());

        allFigures.add(generateFigure8Position1());
        allFigures.add(generateFigure8Position2());
        allFigures.add(generateFigure8Position3());
        allFigures.add(generateFigure8Position4());

        allFigures.add(generateFigure9Position1());
        allFigures.add(generateFigure9Position2());
        allFigures.add(generateFigure9Position3());
        allFigures.add(generateFigure9Position4());
    }

    public MutableGameFigure generateRandomFigure() {
        var bound = allFigures.size();
        var randomIndex = RandomValueGenerator.generateInt(bound);
        return allFigures.get(randomIndex);
    }

    /**
     * Generates figure:
     * <pre>
     * -**
     * -*-
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure1Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 0), blockOf(1, 1),
            blockOf(1, 2), blockOf(2, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * *--
     * ***</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure1Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0), blockOf(0, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * -*-
     * -*-
     * **-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure1Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(1, 1), blockOf(1, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * ***
     * --*</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure1Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(2, 1), blockOf(2, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * **-
     * -*-
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure2Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 0), blockOf(1, 1),
            blockOf(1, 2), blockOf(0, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * --*
     * ***
     * ---</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure2Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(2, 1), blockOf(2, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * *--
     * *--
     * **-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure2Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(0, 0), blockOf(1, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * ***
     * *--</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure2Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(0, 1),
            blockOf(1, 1), blockOf(2, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * *--
     * **-
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure3Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(1, 1), blockOf(1, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * -**
     * **-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure3Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(1, 1), blockOf(2, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * -*-
     * **-
     * *--</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure3Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(0, 1),
            blockOf(1, 1), blockOf(1, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * **-
     * -**</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure3Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(1, 0), blockOf(2, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * --*
     * --*
     * ***</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure4Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0), blockOf(2, 1),
            blockOf(2, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * *--
     * *--
     * ***</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure4Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ***
     * *--
     * *--</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure4Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(0, 1),
            blockOf(0, 2), blockOf(1, 2),
            blockOf(2, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ***
     * --*
     * --*</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure4Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(1, 2),
            blockOf(2, 2), blockOf(2, 1),
            blockOf(2, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * -*-
     * -*-
     * ***</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure5Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0), blockOf(1, 1),
            blockOf(1, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ***
     * -*-
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure5Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(1, 2),
            blockOf(2, 2), blockOf(1, 1),
            blockOf(1, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * *--
     * ***
     * *--</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure5Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(0, 0), blockOf(1, 1),
            blockOf(2, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * --*
     * ***
     * --*</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure5Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(2, 1), blockOf(2, 2),
            blockOf(2, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * ***
     * ---</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure6Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1), blockOf(2, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * -*-
     * -*-
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure6Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 0), blockOf(1, 1), blockOf(1, 2)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * -*-
     * ---</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure7() {
        List<FigureBlock> blocks = List.of(
            blockOf(1, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * **-
     * *--</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure8Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(0, 1), blockOf(1, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * **-
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure8Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1), blockOf(1, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * -*-
     * **-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure8Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0), blockOf(1, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * *--
     * **-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure8Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(0, 0), blockOf(1, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * *--
     * **-
     * *--</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure9Position1() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 2), blockOf(0, 1),
            blockOf(0, 0), blockOf(1, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * ***
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure9Position2() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 1), blockOf(1, 1),
            blockOf(2, 1), blockOf(1, 0)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * -*-
     * **-
     * -*-</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure9Position3() {
        List<FigureBlock> blocks = List.of(
            blockOf(2, 0), blockOf(2, 1),
            blockOf(2, 2), blockOf(1, 1)
        );
        return new MutableGameFigure(blocks);
    }

    /**
     * Generates figure:
     * <pre>
     * ---
     * -*-
     * ***</pre>
     * @return game figure.
     */
    public MutableGameFigure generateFigure9Position4() {
        List<FigureBlock> blocks = List.of(
            blockOf(0, 0), blockOf(1, 0),
            blockOf(2, 0), blockOf(1, 1)
        );
        return new MutableGameFigure(blocks);
    }

    private FigureBlock blockOf(int x, int y) {
        return figureBlockService.createWith(x, y);
    }
}
