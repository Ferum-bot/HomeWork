# Jigsaw-Client

Common game client. Contains main game logic.

Base game interface:
```java
package com.ferumbot.jigsaw.client.clients;

import com.ferumbot.jigsaw.client.field.models.FieldParams;
import com.ferumbot.jigsaw.client.field.models.GameField;
import com.ferumbot.jigsaw.client.figure.model.Coordinates;
import com.ferumbot.jigsaw.client.figure.model.GameFigure;
import com.ferumbot.jigsaw.client.stuff.GameStatistics;
import com.ferumbot.jigsaw.client.exception.GameNotStartedException;
import com.ferumbot.jigsaw.client.field.exception.FigureNotAddedException;

/**
 * Common Jigsaw game client. Provides API for working with game.
 * @see FieldParams
 * @see GameStatistics
 * @see GameFigure
 * @see GameField
 * @author matvejpopov
 * @version 1.0.0
 */
public interface JigsawGameClient {

    /**
     * Creates common instance of Jigsaw game.
     * @return Jigsaw game client.
     */
    static JigsawGameClient getInstance() {
        return new DefaultJigsawClient();
    }

    /**
     * Starts game with default field parameters.
     * Default field size: 9
     */
    void startGame();

    /**
     * Starts game with passing field parameters.
     * @param fieldParams to start game with.
     * @see FieldParams
     */
    void startGame(FieldParams fieldParams);

    /**
     * Finishes game process.
     * @return the statistics of game session.
     * @see GameStatistics
     */
    GameStatistics finishGame();

    /**
     * Adds figure to game field.
     * @param figure figure to add.
     * @param targetCoordinates coordinates to add figure on.
     * @see GameFigure
     * @see Coordinates
     * @throws GameNotStartedException if game wasn't started.
     * @return true if figure was added and false otherwise.
     */
    boolean addFigureToField(GameFigure figure, Coordinates targetCoordinates);

    /**
     * Trying to add figure to game field.
     * @param figure figure to add.
     * @param targetCoordinates coordinates to add figure on.
     * @see GameFigure
     * @see Coordinates
     * @throws GameNotStartedException if game wasn't started.
     * @throws FigureNotAddedException if figure wasn't added.
     */
    void tryToAddFigureToField(GameFigure figure, Coordinates targetCoordinates);

    /**
     * Generates random game figure.
     * @return random game figure.
     * @throws GameNotStartedException if game wasn't started.
     * @see GameFigure
     */
    GameFigure generateRandomGameFigure();

    /**
     * Returns current game field.
     * @return game field of current game session.
     * @throws GameNotStartedException if game wasn't started.
     * @see GameField
     */
    GameField getGameField();
}
```

### Game entities:
* [GameFigure](src/main/java/com/ferumbot/jigsaw/client/figure/model/GameFigure.java) - represents game figure. 
* [GameField](src/main/java/com/ferumbot/jigsaw/client/field/models/GameField.java) - represents game field.
* [FigureBlock](src/main/java/com/ferumbot/jigsaw/client/figure/block/model/FigureBlock.java) - represents figure block.
* [FieldBlock](src/main/java/com/ferumbot/jigsaw/client/field/models/FieldBlock.java) - represents field block.


#### Created and powered by Matvey Popov.
#### Test Coverage 76%.