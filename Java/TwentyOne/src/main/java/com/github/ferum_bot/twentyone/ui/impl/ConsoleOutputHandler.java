package com.github.ferum_bot.twentyone.ui.impl;

import com.github.ferum_bot.twentyone.models.game.GameParameters;
import com.github.ferum_bot.twentyone.models.players.PlayerInfo;
import com.github.ferum_bot.twentyone.ui.OutputHandler;

import java.util.Collection;

public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void OnGameStart() {

    }

    @Override
    public void onGameStep(Collection<PlayerInfo> players) {
        showPlayersInfo(players);
    }

    @Override
    public void onGameFinished(Collection<PlayerInfo> players, PlayerInfo winner) {
        printLn("Game was finished!");
        printLn("Game won player: ");
        showPlayerInfo(winner);
        printLn("Others players: ");
        showPlayersInfo(players);
        printLn("Good luck!");
    }

    @Override
    public void aboutGame() {

    }

    @Override
    public void howPlay() {

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
