package ticTacToe.winningstrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {

    Map<Integer, Map<Character, Integer>> map = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();

        //Check if the map is created for particular row
        if(!map.containsKey(row)){
            map.put(row, new HashMap<>());
        }
        Map<Character, Integer> rowMap = map.get(row);

        // First time entry

        if (!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }
        // Update the symbol frequency
        rowMap.put(symbol, rowMap.get(symbol)+1);

        //Checking whether the frequency of the symbol has reached the size of board
        if(board.getBoard().size() == rowMap.get(symbol)){
            return true;
        }
        return false;
    }
}
