package com.zupple.sudoku.shapes;

import com.zupple.sudoku.Board;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Row {
    private List<Block> blockRow = new ArrayList<>();
    private int rowIndex;
    private static List<Integer> listOf9 = new ArrayList<>();
    private Random generate = new Random();

    public Row(int rowIndex) {
        this.rowIndex = rowIndex;
        this.blockRow = blockRow;

        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            tempList.add(i);
        }
        Collections.shuffle(tempList);
        for (int i = 0; i < 9; i++) {
            Block block = new Block(tempList.get(i));
            block.setColumnIndex(i);
            block.setRowIndex(rowIndex);
            blockRow.add(block);
        }
    }

    public static void populateListOf9() {
        for (int i = 1; i <= 9; i++) {
            listOf9.add(i);
        }
    }

    public void shuffle9Blocks() {
        List<Block> shuffledBlocks = new ArrayList<>();
        Collections.shuffle(listOf9);
        for (int i = 0; i < 9; i++) {
            Block block = new Block(listOf9.get(i));
            shuffledBlocks.add(block);
        }
        blockRow = shuffledBlocks;
    }

    public boolean shuffleRowUntilGood(Board board) {
        shuffle9Blocks();
        int currentRow = getRowIndex();
        int attempts = 0;
        boolean isUnique;

        do {
            if (checkAgainstColumns(board, currentRow)) {
                isUnique = checkAgainstSquares(board, currentRow);
            } else isUnique = false;
            attempts++;
        } while (!isUnique && attempts < currentRow * 1000);

        if (isUnique) {
            return true;
        }
        return false;
    }

    public boolean checkAgainstSquares(Board board, int currentRow) {
        if (currentRow % 3 == 0) {
            return true;
        }
        int firstRowInSquare = currentRow / 3 * 3;
        for (int i = firstRowInSquare; i < firstRowInSquare + 3; i++) {
            Square currentSquare = board.getBlockSquare(i);
            if (!checkAgainst1Square(currentSquare, currentRow)) {
                shuffle9Blocks();
                return false;
            }
        }
        return true;
    }

    public boolean checkAgainst1Square(Square square, int currentRow) {
        int rowsToIterate = currentRow - (currentRow / 3 * 3);
        for (int currentIndex = square.getX(); currentIndex < square.getX() + 3; currentIndex++) {
            for (int squareIndex = 0; squareIndex < rowsToIterate * 3; squareIndex++) {
                if (getNum(currentIndex) == square.getBlockValue(squareIndex)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkAgainstColumns(Board board, int currentRow) {
        for (int i = 0; i < 9; i++) {
            Column currentColumn = board.getColumn(i);
            for (int row = 0; row < currentRow; row++) {
                if (getNum(i) == currentColumn.getBlockValue(row)) {
                    shuffle9Blocks();
                    return false;
                }
            }
        }
        return true;
    }

    public void alternateRow9Builder(Board board) {
        for (int i = 0; i < 9; i++) {
            Column column = board.getColumn(i);
            int missingNum = 45;
            for (int j = 0; j < 8; j++) {
                missingNum -= column.getBlockValue(j);
            }
            setNum(i, missingNum);
            column.setBlockValue(8, missingNum);
        }
    }

    public Integer getNum(int index) {
        return blockRow.get(index).getValue();
    }

    public Block getBlock(int index) {
        return blockRow.get(index);
    }

    public void setNum(int index, int value) {
        blockRow.get(index).setValue(value);
    }

    public void setIndexOfBlocksInRow(int firstValue) {
        for (int i = 0, j = firstValue; i < 9; i++, j++) {
            blockRow.get(i).setBoardIndex(j);
        }
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void printRow() {
        for (int i = 0; i < 9; i++) {
            if ((i + 3) % 3 == 0) {
                System.out.print("|");
            }
            if (getNum(i) == 0) {
                System.out.print("|   ");
            } else {
                System.out.print("| " + getNum(i) + " ");
            }
            if (i == 8) {
                System.out.println("||");
            }
        }

    }

//    @Override
//    public String toString() {
//        String rowString = "";
//        for (int i = 0; i < 9; i++) {
//            if ((i + 3) % 3 == 0) {
//                rowString += "|";
//            }
//            if (getNum(i) == 0) {
//                rowString += "|   ";
//            } else {
//                rowString += "| " + getNum(i) + " ";
//            }
//            if (i == 8) {
//                rowString += "||\n";
//            }
//        }
//        return rowString;
//
//    }

    @Override
    public String toString() {
        String rowString = "";
        for (int i = 0; i < 9; i++) {
//            if (getNum(i) == 0) {
//                rowString += "|   ";
//            }
//            else {
            rowString += getNum(i);
//            }
        }
        rowString += "\n";
        return rowString;
    }

    public String toHtml() {
        String rowString = "";

        for (int i = 0; i < 9; i++) {
            String numToAdd = "" + getNum(i);
            if (numToAdd.equals("0")) {
                numToAdd = " ";
            }
            if (i == 2 || i == 5) {
                rowString += "<td class =\"text-center third-cell\">" + numToAdd + "</td>\n";
            } else {
                rowString += "<td class =\"text-center\">" + numToAdd + "</td>\n";
            }
        }
        rowString += "</tr>\n";

        return rowString;
    }

}
