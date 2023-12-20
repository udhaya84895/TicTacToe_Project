package ticTacToe.winningstrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
