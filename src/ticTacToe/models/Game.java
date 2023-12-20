package ticTacToe.models;

import ticTacToe.exceptions.DuplicateSymbolException;
import ticTacToe.exceptions.MoreThanOneBotException;
import ticTacToe.exceptions.PlayerCountMisMatchException;
import ticTacToe.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player Winner;
    private GameState gamestate;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningstrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningstrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.winningstrategies = winningstrategies;
        this.gamestate = GameState.INPROGRESS;
        this.nextPlayerIndex =0;
        this.moves = new ArrayList<>();
    }
    //Comeback and create constructors later

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player player = players.get(nextPlayerIndex);
        Cell cell = player.makeMove(board);
        Move move = new Move(cell, player);
        moves.add(move);

        /*
        Checking for winner
        */
        if(checkWinner(move, board)){
            gamestate = GameState.SUCCESS;
            Winner = player;
            return;
        }
        /*
        Checking for Draw
        */
        if(moves.size() == board.getDimension() * board.getDimension()){
            gamestate = GameState.DRAW;
            return;
        }
        //Update the next player accordingly
        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % players.size();
    }

    private boolean checkWinner(Move move, Board board) {
        for (WinningStrategy winningStrategy : winningstrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }

    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public Game build() throws MoreThanOneBotException, DuplicateSymbolException, PlayerCountMisMatchException {
            /*
             1. validate bot cont <=1
             2. Validate Unique symbol
             3. Validate dimension and player count
             */
            validateBotcount();
            validateUniqueSymbol();
            validateDimensionandPlayerCount();

            return new Game(dimension, players, winningStrategies);
        }

        private void validateDimensionandPlayerCount() throws PlayerCountMisMatchException {
            if(players.size() != (dimension -1)){
                throw new PlayerCountMisMatchException();
            }
        }

        private void validateUniqueSymbol() throws DuplicateSymbolException {
            Set<Character> symbol = new HashSet<>();

            for (Player player : players){
                if(symbol.contains(player.getSymbol())){
                    throw new DuplicateSymbolException();
                }else {
                    symbol.add(player.getSymbol());
                }
            }
        }

        private void validateBotcount() throws MoreThanOneBotException {
            int botCount = 0;

            for(Player player : players){
                if(player.getPlayertype().equals(PlayerType.BOT)){
                    botCount++;
                }
                if(botCount >1){
                    throw new MoreThanOneBotException();
                }
            }
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public GameState getGamestate() {
        return gamestate;
    }

    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
}
