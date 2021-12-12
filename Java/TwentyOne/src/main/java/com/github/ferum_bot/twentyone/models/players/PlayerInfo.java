package com.github.ferum_bot.twentyone.models.players;

/**
 * Player information data holder.
 */
public record PlayerInfo(
    int playerNumber,
    int score,
    boolean isCheater
) {
}
