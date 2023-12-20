package ticTacToe.botplayingstrategy;

import ticTacToe.models.Board;
import ticTacToe.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
