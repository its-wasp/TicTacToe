package org.example.models;

public class Cell {
    Integer row;
    Integer col;
    Player player;
    // enum for cell state
    public enum CellState {
        X, O, EMPTY
    }
    CellState state;

}
