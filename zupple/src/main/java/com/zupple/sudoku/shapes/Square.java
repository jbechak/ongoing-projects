package com.zupple.sudoku.shapes;

import java.util.ArrayList;
import java.util.List;

public class Square {
    private List<Block> blockSquare = new ArrayList<>();
    private int x;
    private int y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addBlock(Block block) {
        blockSquare.add(block);
    }

    public int getBlockValue(int index) {
        return blockSquare.get(index).getValue();
    }

    public void setBlockValue(int index, int value) {
        blockSquare.get(index).setValue(value);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}


//    public void printSquare() {
//        for (int i = 0; i < 7; i += 3) {
//            System.out.println(getBlockValue(i) + " " + getBlockValue(i + 1) + " " + getBlockValue(i + 2));
//        }
//        System.out.println();
//    }
