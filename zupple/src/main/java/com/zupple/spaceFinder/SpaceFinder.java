package com.zupple.spaceFinder;

import com.zupple.crossword.PlacementResults;
import com.zupple.puzzle.Grid;
import com.zupple.puzzle.Word;

import java.util.Random;

public class SpaceFinder {
    private int[] location = new int[2];
    private Random generate = new Random();

    public int[] scanForWordSpace(Word word, Grid grid) {
        return location;
    }

    public PlacementResults scanForWordSpace(Word word, Grid grid, int[] xy) {
        return null;
    }

    public void assignInitialValues(Grid grid, Word word) {}

    public void assignInitialValues(Grid grid, Word word, int[] xy) {}

    public boolean writeWordInEmptySpace(Word word, Grid grid) {
        int[] location = scanForWordSpace(word, grid);
        int x = location[0];
        int y = location[1];
        if (x >= 0) {
            addWord(word, grid, x, y);
            return true;
        }
        return false;
    }

    public PlacementResults writeWordInEmptySpace(Word word, Grid grid, int[] xy) {
        PlacementResults results = scanForWordSpace(word, grid, xy);
        if (results == null) {
            return null;
        }
        int x = results.getStartingX();
        int y = results.getStartingY();
        addWord(word, grid, x, y);
        return results;
    }

    public void addWord(Word word, Grid grid, int x, int y) {}


    boolean wordSpaceOccupied(int wordStartY, int testX, Word word, Grid grid) {
        for (int testY = wordStartY; testY < wordStartY + word.length(); testY++) { //check each letter in word for this 1 word space
            String currentLetterInWord = word.getChar(testY - wordStartY);
            if (hasLetter(testX, testY, currentLetterInWord, grid)) {
                return true;
            }
        }
        return false;
    }

    public int setWordStart(int j, int firstX, int maxStart){
        int wordStart = j + firstX;
        if (wordStart > maxStart) {
            wordStart = wordStart - maxStart - 1;   //determines starting X point of search area for 1 word
        }
        return wordStart;
    }

     public int setTestXY(int i, int firstXY, int maxStartXY) {
        int testXY = i + firstXY;
        if (testXY >= maxStartXY) {
            testXY = testXY - maxStartXY;
        }
        return testXY;
    }

    public int[] foundLocation(int x, int y) {
        location[0] = x;
        location[1] = y;
        return location;
    }

    public boolean hasLetter(int x, int y, String letter, Grid grid) {
        if (!grid.getBlock(x, y).equals(".") && !grid.getBlock(x, y).equals(letter)) {
            return true;
        }
        return false;
    }

    public boolean isBlank(int x, int y, Grid grid) {
        if (grid.getBlock(x, y).equals(".")) {
            return true;
        }
        return false;
    }



}
