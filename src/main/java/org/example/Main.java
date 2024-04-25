package org.example;

import org.example.controllers.GameController;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("HELLO WORLD");

        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> players = List.of(
                new Player("Player 1", new Symbol('X'), PlayerType.HUMAN),
                new Bot("Bot", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        Game game = gameController.startGame(dimension, players);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            gameController.printBoard(game);

            gameController.makeMove(game);

        }

        if (!gameController.checkState(game).equals(GameState.FINISHED)){
            game.setGameState(GameState.DRAW);
            System.out.println("Game is a draw !");

        }else{
            gameController.printBoard(game);
            System.out.println("Winner is : " + gameController.getWinner(game).getName());
        }

    }
}