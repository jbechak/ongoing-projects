package com.TicTacToe.players;

import com.TicTacToe.Board;
import com.TicTacToe.UserInterface;
import com.TicTacToe.players.Player;

import java.util.List;
import java.util.Random;

public class SmartBotPlayer extends Player {
    Random random = new Random();

    public SmartBotPlayer(String name) {
        super(name);
    }

    @Override
    public void playerDecision(Board ticTacToeBoard, List<Integer> opponentBlocks, List<List> winSet) {
        System.out.println();
        int choiceAsInt = 1;
        int rowWithTwo = -1;

        if (opponentBlocks.size() >= 2) {
            rowWithTwo = findRowToBlockOpponent(opponentBlocks, winSet);

            if (rowWithTwo >= 0) {
                choiceAsInt = findOpenBlockInRow(opponentBlocks, winSet.get(rowWithTwo));
            }
        } else {
            do {
                choiceAsInt = random.nextInt(8) + 1;
            } while (ticTacToeBoard.alreadyPlayed(choiceAsInt));
        }

        ticTacToeBoard.setArrayIndex(choiceAsInt, getXorO());
        updateScoringList(choiceAsInt);
        UserInterface.announcePlayerMove(getName(), choiceAsInt, getXorO());
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
