package ticTacToe.AppRunner;

import ticTacToe.controller.GameController;
import ticTacToe.exceptions.DuplicateSymbolException;
import ticTacToe.exceptions.MoreThanOneBotException;
import ticTacToe.exceptions.PlayerCountMisMatchException;
import ticTacToe.models.*;
import ticTacToe.winningstrategies.ColWinningStrategy;
import ticTacToe.winningstrategies.DiagnalWinningStrategy;
import ticTacToe.winningstrategies.RowWinningStrategy;
import ticTacToe.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws DuplicateSymbolException, PlayerCountMisMatchException, MoreThanOneBotException {
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> playerList = new ArrayList<>();
        List<WinningStrategy> winningStrategies = new ArrayList<>();

        playerList.add(new Player('X', "Udhay", 1, PlayerType.HUMAN));
        playerList.add(new Bot('O', "GPT", 2, PlayerType.BOT, botDifficultyLevel.EASY));

        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagnalWinningStrategy());

        Game game = gameController.startGame(dimension, playerList, winningStrategies);

        while(game.getGamestate().equals(GameState.INPROGRESS)){
            game.printBoard();
            gameController.makeMove(game);
        }
        //If I'm here, it means game is not in progress
        if(GameState.SUCCESS.equals(game.getGamestate())){
            System.out.println("Hello " + game.getWinner().getName() + ", Congrats you won the game champ :)");
        }if(GameState.DRAW.equals(game.getGamestate())){
            System.out.println("Uff match draw :)");
        }
    }
}
