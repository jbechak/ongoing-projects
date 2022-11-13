package com.zupple.sudoku.shapes;

import java.util.ArrayList;
import java.util.List;

public class TripleRow {
    List<Row> tripleRow = new ArrayList<>();
    int topRow;

    public TripleRow(int topRow) {
        this.topRow = topRow;
    }

    public List<Row> getTripleRow() {
        return tripleRow;
    }

//    public void printTripleRow() {
//        for (int i = 0; i < 3; i++) {
//            getRow(i).printRow();
//            if (i < 2) {
//                printHorizontalLine();
//            }
//        }
//        printDoubleHorizontalLine();
//    }

    @Override
    public String toString() {
        String row = "";
        for (int i = 0; i < 3; i++) {
            row += getRow(i);
            if (i < 2) {
                row += line();
            }
        }
        return row;
    }

    public String line() {
        String line = "";
        for (int i = 0; i < 9; i++) {
            line += "----";
            if (i == 8) {
                line += "-----\n";
            }
        }
        return line;
    }

//    public void printHorizontalLine() {
//        for (int i = 0; i < 9; i++) {
//            System.out.print("----");
//            if (i == 8) {
//                System.out.println("-----");
//            }
//        }
//    }

//    public void printDoubleHorizontalLine() {
//        for (int i = 0; i < 9; i++) {
//            System.out.print("====");
//            if (i == 8) {
//                System.out.println("=====");
//            }
//        }
//    }

    public int getBlock(int x, int y) {
        Row thisRow = getRow(y);
        return thisRow.getNum(x);

    }

    public Row getRow(int y) {
        return tripleRow.get(y);
    }

    public int getTopRow() {
        return topRow;
    }

    public void addRow(Row row) {
        tripleRow.add(row);
    }
}
