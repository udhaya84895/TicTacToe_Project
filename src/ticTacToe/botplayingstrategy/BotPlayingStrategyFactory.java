package ticTacToe.botplayingstrategy;

import ticTacToe.models.botDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel botdifficultylevel) {
        // I need to implement the if else for easy playing strategy
        return new EasyBotPlayingStrategy();
    }
}
