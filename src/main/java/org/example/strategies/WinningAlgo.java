package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;

import java.util.HashMap;

public class WinningAlgo {

    HashMap<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();
    HashMap<Integer, HashMap<Character, Integer>> colMaps = new HashMap<>();
    HashMap<Character, Integer> leftDiagMap = new HashMap<>();
    HashMap<Character, Integer> rightDiagMap = new HashMap<>();

    public boolean checkWinner(Board board, Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character character = move.getPlayer().getSymbol().getaChar();

        // row

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row, new HashMap<>());
        }
        HashMap<Character, Integer> currRowMap = rowMaps.get(row);

        if(!currRowMap.containsKey(character)){
            currRowMap.put(character, 1);
        }
        else{
            currRowMap.put(character, currRowMap.get(character) + 1);
        }

        if(currRowMap.get(character) == board.getSize()){
            return true;
        }

        // col
        if(!colMaps.containsKey(col)){
            colMaps.put(col, new HashMap<>());
        }

        HashMap<Character, Integer> currColMap = colMaps.get(col);

        if(!currColMap.containsKey(character)){
            currColMap.put(character, 1);
        }
        else{
            currColMap.put(character, currColMap.get(character) + 1);
        }

        if(currColMap.get(character) == board.getSize()){
            return true;
        }

        // left diagonal

        if(row == col){
            if(!leftDiagMap.containsKey(character)){
                leftDiagMap.put(character, 1);
            }
            else{
                leftDiagMap.put(character, leftDiagMap.get(character) + 1);
            }

            if(leftDiagMap.get(character) == board.getSize()){
                return true;
            }
        }

        // right diagonal

        if(row + col == board.getSize() - 1){
            if(!rightDiagMap.containsKey(character)){
                rightDiagMap.put(character, 1);
            }
            else{
                rightDiagMap.put(character, rightDiagMap.get(character) + 1);
            }

            if(rightDiagMap.get(character) == board.getSize()){
                return true;
            }
        }

        return false;

    }
}
