package battleship.ui.output;

import battleship.game.action_result.TorpedoHitResult;
import battleship.models.field.GameField;
import battleship.game.action_result.HitResult;
import battleship.models.field.coordinate.FieldCoordinate;
import battleship.models.statistics.Statistics;
import battleship.ui.util.ConsoleUtil;

import java.util.List;

public class ConsoleOutputInfoProvider implements OutputGameInfoProvider {

    private static final String EMPTY_HIT_SIGN = "$";
    private static final String SHIP_HIT_SIGN = "?";
    private static final String SHIP_SUNK_SIGN = "!";
    private static final String UN_FIRED_SIGN = "#";

    @Override
    public void onGameStarted() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Hello!\n")
                .append("This is Battle ship game!\n")
                .append("The rules are simple, I have a filed with fixed size.\n")
                .append("You write some game params(field size, number of ships, game mode).\n")
                .append("I generate a filed, if it possible, and than the game starts!\n")
                .append("You input the hit coordinates and I will tell did you hit or not\n")
                .append("Try to sunk all the ships in the minimum number of moves!\n")
                .append("Have fun and enjoy!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void onGameBegins() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("The game begins!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void onGameCanceled(Statistics statistics) {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("The game was Canceled!\n")
                .append(parseGameStatistics(statistics))
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void onGameWined(Statistics statistics) {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("You win the game!\n")
                .append(parseGameStatistics(statistics))
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void showGameSettingsHint() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Not let's configure the game!\n\n")
                .append("Short Terms of Use\n")
                .append("Available commands in any moment:\n")
                .append(getAllCommandsInfo())
                .append("\n")
                .append("Available ships by it number:\n")
                .append("  1. Carrier(5 cells)\n")
                .append("  2. Battleship(4 cells)\n")
                .append("  3. Cruiser(3 cells)\n")
                .append("  4. Destroyer(2 cells)\n")
                .append("  5. Submarine(1 cells) \n")
                .append("\n")
                .append("Available game mods:\n")
                .append("  1. T -> enable a torpedo mode\n")
                .append("  2. R -> enable a recover mode\n")
                .append("\n")
                .append("If you want to get more information about the game and rules.\n")
                .append("Write command help at any moment!\n\n")
                .append("Now input the game setting by pattern: N M 1 2 3 4 5 M1 6 M2!\n")
                .append("Example: 10 10 0 1 2 3 4 T 5 R -> That means:\n")
                .append("Field Size: 10x10\n")
                .append("Number of ships by type: 0 -> Carrier, 1 -> Battleship and etc.\n")
                .append("Enabled torpedo mode with 5 torpedoes.\n")
                .append("Enabled recovery mode.\n")
                .append("Now your settings!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void showHowToPlay() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Hot to play:\n\n")
                .append("First you must input the game settings. The pattern of game settings is:\n")
                .append("W H 1 2 3 4 5 EXTRA_MODE_1 EXTRA_MODE_2\n")
                .append("What does it means:\n")
                .append("W -> game field width.\n")
                .append("H -> game field height.\n")
                .append("1 -> Carrier count(5 cells).\n")
                .append("2 -> Battleship count(4 cells).\n")
                .append("3 -> Cruise count(3 cells).\n")
                .append("4 -> Destroyer count(2 cells).\n")
                .append("5 -> Submarine count(1 cells).\n")
                .append("Than, you can activate two game mods: Torpedo mode and Recovery mode.\n")
                .append("To activate Torpedo mode, add this after ships count 'T <TORPEDOES_COUNT>'.\n")
                .append("For example, to start game with width 5, height 7, carrier count 1, battleship count 0, cruiser count 1,\n")
                .append("destroyer count 0, submarine count 1 and enable Torpedo mode with 3 torpedoes, write this:\n")
                .append("5 7 1 0 1 0 1 T 3\n")
                .append("If you want to activate Recovery mode, just add a 'R' alias after ships count!\n")
                .append("For example:\n")
                .append("5 7 1 0 1 0 1 R\n")
                .append("If you want to activate both Torpedoes and Recovery modes, write this:\n")
                .append("5 7 1 0 1 0 1 T 3 R\n\n")
                .append("In any moment you can write this commands:\n")
                .append("  * help -> will show the full information about the game and available possibilities\n")
                .append("  * exit -> will exit from game\n")
                .append("All available commands in a game:\n")
                .append(getAllCommandsInfo())
                .append("About game field:\n\n")
                .append("The field coordinates start at (1, 1) to (WIDTH, HEIGHT)\n")
                .append("The left top corner is (1, 1) and right bottom corner is (WIDTH, HEIGHT)\n")
                .append("Horizontally this is the x-axis, vertically this is the Y-axis\n")
                .append("The field cells aliases:\n")
                .append(getFieldAliasesInfo())
                .append("For example, field with width 4 and height 5:\n\n")
                .append("    ##$#\n")
                .append("    #!##\n")
                .append("    #!##\n")
                .append("    ##?#\n")
                .append("    ####\n")
                .append("\n\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void incorrectCommand() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("This command is not available now!\n")
                .append("If you want to see all available commands, write 'help'!\n")
                .append("If you want to exit game, write 'exit'!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void incorrectGameSettings() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Invalid game parameters!\n")
                .append("I can't generate game field with this parameters\n")
                .append("Please, try again!\n")
                .append("If you want to see all available commands, write 'help'!\n")
                .append("If you want to exit game, write 'exit'!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void invalidHitCommand() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Invalid Hit command!\n")
                .append("The coordinates must be from 1 to width/height of the field!\n")
                .append("Please try again!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void showHitResult(HitResult result) {
        var text = new StringBuilder().append(ConsoleUtil.getSeparator());

        switch (result) {
            case SUNK -> {
                text.append("You just have sunk a ")
                    .append(result.getShipName())
                    .append("\n");
            }
            case MISSED -> {
                text.append("You missed!\n");
            }
            case HIT -> {
                text.append("You hit the ship!\n");
            }
        }
        text.append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void showTorpedoHitResult(TorpedoHitResult result) {
        switch (result) {
            case MISSED -> torpedoMissed(result.getAvailableTorpedoCount());
            case SUNK -> torpedoSunkShip(result.getAvailableTorpedoCount(), result.getShipName());
            case NO_AVAILABLE_TORPEDOES -> noAvailableTorpedo();
        }
    }

    @Override
    public void undefinedCommand() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Opps, I can't define the command!\n")
                .append("If you want to see all available commands, write 'help'!\n")
                .append("If you want to exit game, write 'exit'!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void showGameField(GameField field) {
        printGameFieldInfo();
        printGameField(field);
    }

    private void print(String text) {
        System.out.print(text);
    }

    private void printGameFieldInfo() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Current game field:\n\n")
                .append("Cells aliases:\n")
                .append(getFieldAliasesInfo());

        print(text.toString());
    }

    private void printGameField(GameField field) {
        var gameField = field.getField();
        for (List<FieldCoordinate> fieldRow : gameField) {
            for (FieldCoordinate cell : fieldRow) {
                parseFieldCell(cell);
            }
            print("\n");
        }
        print("\n");
        print("Now your turn!\n");
        print(ConsoleUtil.getSeparator());
    }

    private void parseFieldCell(FieldCoordinate cell) {
        var status = cell.getStatus();
        switch (status) {
            case NOT_SHOOT -> print(UN_FIRED_SIGN);
            case EMPTY_SHOT -> print(EMPTY_HIT_SIGN);
            case SHIP_SHOT -> print(SHIP_HIT_SIGN);
            case SHIP_SUNK -> print(SHIP_SUNK_SIGN);
        }
    }

    private StringBuilder parseGameStatistics(Statistics statistics) {
        return new StringBuilder()
                .append("Your Score: ")
                .append(statistics.userScore())
                .append("\n")
                .append("Total missed shots: ")
                .append(statistics.missedHits())
                .append("\n")
                .append("General ships sunk count: ")
                .append(statistics.generalShipsSunk())
                .append("\n")
                .append("Carrier ships sunk count: ")
                .append(statistics.carrierSunkCount())
                .append("\n")
                .append("Battleship ships sunk count: ")
                .append(statistics.battleshipSunkCount())
                .append("\n")
                .append("Cruiser ships sunk count: ")
                .append(statistics.cruiserSunkCount())
                .append("\n")
                .append("Destroyer ships sunk count: ")
                .append(statistics.destroyerSunkCount())
                .append("\n")
                .append("Submarine ships sunk count: ")
                .append(statistics.submarineSunkCount())
                .append("\n");
    }

    private void noAvailableTorpedo() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("No available Torpedoes!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    private void torpedoMissed(Integer availableTorpedoCount) {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Your Torpedo missed!\n")
                .append("Available Torpedo count: ")
                .append(availableTorpedoCount)
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    private void torpedoSunkShip(Integer availableTorpedoCount, String shipName) {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Your Torpedo just have sunk a ")
                .append(shipName)
                .append("\n")
                .append("Available Torpedo count: ")
                .append(availableTorpedoCount)
                .append("\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    private StringBuilder getFieldAliasesInfo() {
        return new StringBuilder()
                .append("1. ")
                .append(EMPTY_HIT_SIGN)
                .append(" -> Means that the hit on this cell was missed.\n")
                .append("2. ")
                .append(UN_FIRED_SIGN)
                .append(" -> Means that cell is unfired.\n")
                .append("3. ")
                .append(SHIP_HIT_SIGN)
                .append(" -> Means that the hit on this cell damaged ship.\n")
                .append("4. ")
                .append(SHIP_SUNK_SIGN)
                .append(" -> Means that the ship is sunk in this cell.\n\n");
    }

    private StringBuilder getAllCommandsInfo() {
        return new StringBuilder()
                .append("  * help -> will show the full information about the game and available possibilities\n")
                .append("  * exit -> will exit from game\n")
                .append("  * x y  -> will hit at x and y coordinate\n")
                .append("  * x y T -> will launch a torpedo, if this mode is active\n");
    }
}
