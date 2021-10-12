package battleship;

import battleship.game.GameController;
import battleship.game.settings.HardwareSettings;
import battleship.ui.input.ConsoleInputHandler;
import battleship.ui.output.ConsoleOutputInfoProvider;

public class Main {

    public static void main(String[] args) {
        var hardwareSettings = getHardwareSettings();
        var gameController = new GameController(hardwareSettings);

        gameController.startGame();
    }

    private static HardwareSettings getHardwareSettings() {
        var inputHandler = new ConsoleInputHandler();
        var outputProvider = new ConsoleOutputInfoProvider();
        return new HardwareSettings(inputHandler, outputProvider);
    }
}
