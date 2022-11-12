package com.zupple.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
    private List<List> grid = new ArrayList<>();
    private int height;
    private int width;
    private int FULL_WIDTH = 81;
    private int usedSpaces = 0;


    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < height; i++) {
            List<String> line = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                line.add(".");
            }
            grid.add(line);
        }
    }

    public void updateUsedSpaces(int newSpaces) {
        usedSpaces += newSpaces;
    }

    public int getUsedSpaces() {
        return usedSpaces;
    }

    public int totalSpaces() {
        return height * width;
    }

    public int remainingSpaces() {
        return totalSpaces() - usedSpaces;
    }



    @Override
    public String toString() {
        String gridString = "";
        for (int i = 0; i < height; i++) {
            gridString += tabToCenter(width * 3);
            for (int j = 0; j < width; j++) {
                gridString += (getBlock(j, i) + "  ");
            }
            gridString += "\n";
        }
        return gridString;
    }

    private String tabToCenter(int wordLength) {
        String space = "";
        int spaces = (FULL_WIDTH - wordLength) / 2;
        for (int i = 0; i < spaces; i++) {
            space += " ";
        }
        return space;
    }

    public void printGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                 System.out.print(getBlock(j, i) + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setBlock(int x, int y, String letter) {
        List<String> line = getRow(y);
        line.set(x, letter);
    }

    public String getBlock(int x, int y) {
        List<String> line = getRow(y);
        return line.get(x);
    }

    public List<String> getRow(int y) {
        return grid.get(y);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void turnZerosToBlanks() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (getBlock(i, j).equals("0")) {
                    setBlock(i, j, ".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fillWithRandomLetters() {
        Random generate = new Random();
        for (int i = 0; i < width; i ++) {
            for (int j = 0; j < height; j++) {
                if (getBlock(i, j).equals(".")) {
                    int num = generate.nextInt(26);
                    String letter = String.valueOf((char)(num + 65));
                    setBlock(i, j, letter);
                }
            }
        }
    }

    public void clearGrid() {
        grid.clear();
    }

}
