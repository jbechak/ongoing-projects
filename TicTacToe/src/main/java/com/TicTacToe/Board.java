package com.TicTacToe;

import java.util.*;

public class Board {
    private String[] array = new String[9];
    private String emptySpace = "       ";
    private String horizontalLine = "-------";
    private String emptyRow;
    private int arrayIndex = 0;

    public Board() {
        for (int i = 0; i < 9; i++) {
            this.array[i] = "(" + (i + 1) + ")";
        }
    }

    public void printBoard() {
        emptyRow = emptySpace + "|" + emptySpace + "|";
        System.out.println("\n\n\n");
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                System.out.println(emptyRow);
            }
            if ((i + 3) % 4 == 0) {
                System.out.println("  " + (array[iToArrayIndex(i)]) + "  |  " + (array[iToArrayIndex(i) + 1]) + "  |  " + (array[iToArrayIndex(i) + 2]));
            }
            if (i == 3 || i == 7) {
                System.out.println(horizontalLine + "+" + horizontalLine + "+" + horizontalLine);
            }
        }
    }

    public int iToArrayIndex(int i) {
        return (i + 3) * 3 / 4 - 3;
    }

    public void setArrayIndex(int block, String xorO) {
        array[block - 1] = " " + xorO + " ";
        printBoard();
    }

    public boolean alreadyPlayed(int choice) {
        if (array[choice - 1].equals(" X ") || array[choice - 1].equals(" O ")) {
            return true;
        } return false;
    }

    public void printArray() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public void resetBoard() {
        //populate array
        for (int i = 0; i < 9; i++) {
            this.array[i] = "(" + (i + 1) + ")";
        }
        printBoard();
    }

}



/*
       |       |
  (1)  |  (2)  |  (3)
       |       |
-------+-------+-------
       |       |
  (4)  |  (5)  |  (6)
       |       |
-------+-------+-------
       |       |
  (7)  |  (8)  |  (9)
       |       |




 */