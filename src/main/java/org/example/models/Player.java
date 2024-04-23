package org.example.models;

public class Player {
    String name;
    Symbol symbol;
    // enum for player type;

    public enum PlayerType {
        HUMAN, BOT
    }
    PlayerType type;
}
