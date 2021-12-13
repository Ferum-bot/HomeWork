package com.github.ferum_bot.twentyone.ui.impl;

import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.models.players.PlayerInfo;
import com.github.ferum_bot.twentyone.ui.OutputHandler;

import java.util.Collection;

public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void onGameStart() {
        printLn("Hello, this is Twenty one game!");
        printLn("The rules are simple!");
        printLn("Enter the number of common players and cheaters.");
        printLn("First enter the number of common players, then cheaters");
        printLn("The minimum number of common players is two!");
        printLn("The input format is: N1 N2 ");
        printLn("Example: 5 7");
        printLn("Then the game starts!");
        printLn("Game lasts about 10 seconds. Then the winner will be printed!");
        printLn("For more information read HOW_GAME_WORKS.md file!");
    }

    @Override
    public void inputGameParameters() {
        newLine();
        printLn("Now input number of players!");
        printLn("The input format is: N1 N2 ");
        printLn("Example: 5 7");
    }

    @Override
    public void invalidGameParameters() {
        newLine();
        printLn("The input game parameters are invalid!");
        printLn("The minimum number of common players is two!");
        printLn("The maximum number of total players is 100!");
        printLn("Please try again!");
    }

    @Override
    public void parametersIsValid() {
        newLine();
        printLn("Success! The game starts! It will be continued for 10 seconds.");
        printLn("Please wait till game ends.");
    }

    @Override
    public void onGameFinished(Collection<PlayerInfo> players, PlayerInfo winner) {
        newLine();
        newLine();
        printLn("Game was finished!");
        printLn("Game won player: ");
        showPlayerInfo(winner);
        printLn("Others players: ");
        showPlayersInfo(players);
        printLn("Good luck!");
    }

    private void showPlayersInfo(Collection<PlayerInfo> playersInfo) {
        playersInfo.forEach(this::showPlayerInfo);
    }

    private void showPlayerInfo(PlayerInfo playerInfo) {
        printLn("Player number: " + playerInfo.playerNumber());

        print("1. Player type: ");
        if (playerInfo.isCheater()) {
            printLn("Cheater");
        } else {
            printLn("Common Player");
        }

        printLn("2. Score: " + playerInfo.score());
        newLine();
    }

    private void print(String string) {
        System.out.print(string);
    }

    private void printLn(String string) {
        System.out.println(string);
    }

    private void newLine() {
        System.out.print("\n");
    }
}
