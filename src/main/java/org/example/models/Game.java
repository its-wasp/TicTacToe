package org.example.models;

import org.example.exceptions.InvalidMoveException;
import org.example.strategies.WinningAlgo;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextPlayerMoveIndex;
    private WinningAlgo winningAlgo;


    public Game(int dimension, List<Player> players){
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerMoveIndex = 0;
        this.winningAlgo = new WinningAlgo();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public void printBoard(){
        this.board.printBoard();
    }

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || row >= this.board.getSize() || col < 0 || col >= this.board.getSize()){
            return false;
        }

        return board.getBoard().get(row).get(col).getState().equals(CellState.EMPTY); // returns true if cell is empty else false


    }

    public void makeMove() throws InvalidMoveException {
        Player currPlayer = players.get(nextPlayerMoveIndex);

        System.out.printf(currPlayer.getName() + "'s turn\n");


        Move move = currPlayer.makeMove(board);

        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid Move by " + currPlayer.getName());

        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);

        cellToChange.setPlayer(currPlayer);
        cellToChange.setState(CellState.FILLED);

        Move finalmove = new Move(cellToChange, currPlayer);
        moves.add(finalmove);
        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

        // check if the current move is the winning move or not

        if(winningAlgo.checkWinner(board, finalmove)){
            gameState = GameState.FINISHED;
            winner = currPlayer;
        }
    }


}
