package org.example.models;

import java.util.List;

public class Game {
    private Board board;
    List<Player> players;
    Integer nextPlayerMoveIndex;
    Player winner;
    List<Move> moves;
    // enum for GameState
    public enum GameState {
        IN_PROGRESS, ENDED, DRAW
    }
    GameState state;
}
