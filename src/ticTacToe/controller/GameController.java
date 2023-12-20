package ticTacToe.controller;

import ticTacToe.exceptions.DuplicateSymbolException;
import ticTacToe.exceptions.MoreThanOneBotException;
import ticTacToe.exceptions.PlayerCountMisMatchException;
import ticTacToe.models.Game;
import ticTacToe.models.Player;
import ticTacToe.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> playerlist, List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayerCountMisMatchException, MoreThanOneBotException {
        return Game.getBuilder().setDimension(dimension)
                .setPlayers(playerlist)
                .setWinningStrategies(winningStrategies)
                .build();
    }
    public void printBoard (Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }












}
