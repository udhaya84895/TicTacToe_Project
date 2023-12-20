package ticTacToe.winningstrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{

    Map<Integer, Map<Character, Integer>> map = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        //Check if the map is created for particular row
        if(!map.containsKey(col)){
            map.put(col, new HashMap<>());
        }
        Map<Character, Integer> colMap = map.get(col);

        // First time entry

        if (!colMap.containsKey(symbol)){
            colMap.put(symbol,0);
        }
        // Update the symbol frequency
         colMap.put(symbol, colMap.get(symbol)+1);

        //Checking whether the frequency of the symbol has reached the size of board
        if(board.getBoard().size() == colMap.get(symbol)){
            return true;
        }
        return false;
    }

    @Override
    public void undo(Board board, Move lastMove) {
        int col = lastMove.getCell().getCol();
        char symbol = lastMove.getPlayer().getSymbol();

        Map<Character, Integer> colMap = map.get(col);
        colMap.put(symbol, colMap.get(symbol)-1);
    }
}

