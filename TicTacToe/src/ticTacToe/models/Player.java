package ticTacToe.models;

import java.util.Scanner;

public class Player {
    private char Symbol;
    private String name;
    private int id;
    private PlayerType playertype;
    private Scanner scanner;

    public Player(char symbol, String name, int id, PlayerType playertype) {
        Symbol = symbol;
        this.name = name;
        this.id = id;
        this.playertype = playertype;
        scanner = new Scanner(System.in);
    }

    public char getSymbol() {
        return Symbol;
    }

    public void setSymbol(char symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayertype() {
        return playertype;
    }

    public void setPlayertype(PlayerType playertype) {
        this.playertype = playertype;
    }

    public Cell makeMove(Board board) {
        System.out.println(this.name + ", It's yourn turn. Enter row and col :");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while (!validateMove(row, col, board)){
            System.out.println(this.name + ", Invalid move");
             row = scanner.nextInt();
             col = scanner.nextInt();
        }
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellstate(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }

    private boolean validateMove(int row, int col, Board board) {
        //Checking whether the row and col are not Out of Boundary
        if(row >= board.getDimension()){
            return false;
        }
        if(col >= board.getDimension()){
            return false;
        }
        //checking whether the cell is empty
        if(!CellState.EMPTY.equals(board.getBoard().get(row).get(col).getCellstate())){
            return false;
        }
        return true;
    }
}
