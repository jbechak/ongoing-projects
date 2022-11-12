package com.TicTacToe.players;

import com.TicTacToe.Board;
import com.TicTacToe.UserInterface;

import java.util.List;
import java.util.Random;

public class SmartestBotPlayer extends Player {
    Random random = new Random();
    private boolean success = false;

    public SmartestBotPlayer(String name) {
        super(name);
    }

    @Override
    public void playerDecision(Board ticTacToeBoard, List<Integer> opponentBlocks, List<List> winSet) {
        System.out.println();

            int nextMove = 1;
        do {
            int rowWithTwo = 1;
            success = false;
            nextMove = completeRowForWin(ticTacToeBoard, winSet);

            if (!success) {
                if (opponentBlocks.size() >= 2) {           //defensive play against 2 in a row
                    rowWithTwo = findRowToBlockOpponent(opponentBlocks, winSet);

                    if (rowWithTwo >= 0) {
                        nextMove = findOpenBlockInRow(opponentBlocks, winSet.get(rowWithTwo));
                    }
                } else if (opponentBlocks.size() == 1) {    //defense against 1st move
                    nextMove = placeFirstO(ticTacToeBoard, opponentBlocks, winSet);
                } else {
                    do {                                    //random play
                        nextMove = random.nextInt(8) + 1;
                    } while (ticTacToeBoard.alreadyPlayed(nextMove));
                }
            }
        } while (ticTacToeBoard.alreadyPlayed(nextMove));
        ticTacToeBoard.setArrayIndex(nextMove, getXorO());
        updateScoringList(nextMove);
        UserInterface.announcePlayerMove(getName(), nextMove, getXorO());
    }

    public int completeRowForWin(Board ticTacToeBoard, List<List> winSet) {
        int nextMove = 0;
        int myCounter = 0;

        for (int i = 0; i < winSet.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (getScoringList().contains(winSet.get(i).get(j))) {
                    myCounter++;
                } else {
                    nextMove = (int) winSet.get(i).get(j);
                }
            }
            if (myCounter == 2 && !ticTacToeBoard.alreadyPlayed(nextMove)) {
                success = true;
                return nextMove;
            }
            myCounter = 0;
        }
        return 1;
    }

    public int placeFirstO(Board ticTacToeBoard, List<Integer> opponentBlocks, List<List> winSet) {
        int nextMove = 0;
        boolean[] possibilities = new boolean[10];
        for (boolean possibility : possibilities) {
            possibility = false;
        }
        boolean newNumberNeeded = true;

        for (int i = 0; i < winSet.size(); i++) {
            if (winSet.get(i).contains(opponentBlocks.get(0))) {
                for (int j = 0; j < 3; j++) {
                    possibilities[(int) winSet.get(i).get(j)] = true;
                }
            }
        }
        do {
            nextMove = random.nextInt(8) + 1;
            for (int i = 1; i <= 8; i++) {
                if (possibilities[i] && nextMove == i) {
                    newNumberNeeded = false;
                    break;
                }
            }
        } while (newNumberNeeded || ticTacToeBoard.alreadyPlayed(nextMove));

        return nextMove;
    }

    public int findRowToBlockOpponent(List<Integer> opponentBlocks, List<List> winSet) {
        int otherPlayerCounter = 0;
        int myCounter = 0;

        for (int i = 0; i < winSet.size(); i++) {

            for (int j = 0; j < 3; j++) {
                if (opponentBlocks.contains(winSet.get(i).get(j))) {
                    otherPlayerCounter++;

                } else {
                    if (getScoringList().contains(winSet.get(i).get(j))) {
                        myCounter++;
                    }
                }
            }
            if (otherPlayerCounter == 2 && myCounter < 1) {
                return i;
            }
            otherPlayerCounter = 0;
            myCounter = 0;
        }
        return -1;
    }

    public int findOpenBlockInRow(List<Integer> opponentBlocks, List<Integer> winningGroup) {
        for (int i = 0; i < 3; i++) {
            if (!opponentBlocks.contains(winningGroup.get(i))) {
                return (int) winningGroup.get(i);
            }
        }
        return 0;
    }


}
