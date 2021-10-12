package battleship.ui.output;

import battleship.models.field.GameField;
import battleship.models.statistics.Statistics;
import battleship.ui.util.ConsoleUtil;

public class ConsoleOutputInfoProvider implements OutputGameInfoProvider {

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
    public void showGameSettingsHint() {
        var text = new StringBuilder()
                .append(ConsoleUtil.getSeparator())
                .append("Not let's configure the game!\n\n")
                .append("Short Terms of Use\n")
                .append("Available commands in any moment:\n")
                .append("  * help -> will show the full information about the game and available possibilities\n")
                .append("  * exit -> will exit from game\n")
                .append("  * x y  -> will hit at x and y coordinate\n")
                .append("  * T x y -> will launch a torpedo, if this mode is active\n")
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
                .append("Now input the game setting by pattern: N M 1 2 3 4 5 M1 M2!\n")
                .append("Example: 10 10 0 1 2 3 4 5 T R -> That means:\n")
                .append("Field Size: 10x10\n")
                .append("Number of ships by type: 0 -> Carrier, 1 -> Battleship and etc.\n")
                .append("Enabled torpedo mode and recovery mode\n")
                .append("Not your settings!\n")
                .append(ConsoleUtil.getSeparator());

        print(text.toString());
    }

    @Override
    public void showHowToPlay() {

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
    public void onAwaitingHitCoordinate(GameField field) {

    }

    @Override
    public void onGameCanceled(Statistics statistics) {

    }

    @Override
    public void onGameWined(Statistics statistics) {

    }

    private void print(String text) {
        System.out.print(text);
    }
}
