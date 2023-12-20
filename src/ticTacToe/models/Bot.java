package ticTacToe.models;

import ticTacToe.botplayingstrategy.BotPlayingStrategy;
import ticTacToe.botplayingstrategy.BotPlayingStrategyFactory;

public class Bot extends Player{
    private botDifficultyLevel botdifficulty;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(char symbol, String name, int id, PlayerType playertype, botDifficultyLevel botdifficultylevel) {
        super(symbol, name, id, playertype);
        this.botdifficulty = botdifficulty;
        this.botPlayingStrategy =
                BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botdifficultylevel);
    }

    @Override
    public Cell makeMove(Board board){
        System.out.println("Bot made a move :)");
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setCellstate(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }
}
