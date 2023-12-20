package ticTacToe.winningstrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagnalWinningStrategy implements WinningStrategy{

    Map<Character, Integer> leftDiagonalMap = new HashMap<>();
    Map<Character, Integer> rightDiagonalMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        char symbol = move.getPlayer().getSymbol();

        //Check if the cell is part of leftDiagonal
        if(row == col){
            //Check if the cell is coming from left diagonal
            if(!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol,0);
            }
            leftDiagonalMap.put(symbol,leftDiagonalMap.get(symbol)+1);
            //check if frequency of current symbol is equal to board size
            if(leftDiagonalMap.get(symbol) == board.getDimension()){
                return true;
            }
        }
        //Check if cell is from rightDiagonal

        if((row + col) == board.getDimension()-1){
            if(!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol,0);
            }
            rightDiagonalMap.put(symbol,rightDiagonalMap.get(symbol)+1);

            //check if the frequency of current symbol is equal to board dimension
            if(rightDiagonalMap.get(symbol) == (board.getDimension())){
                return true;
            }
        }

        return false;
    }

    @Override
    public void undo(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        char symbol = lastMove.getPlayer().getSymbol();

        if(row==col){
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)-1);
        }
        if((row+col)== board.getDimension() -1){
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol)-1);
        }
    }
}
