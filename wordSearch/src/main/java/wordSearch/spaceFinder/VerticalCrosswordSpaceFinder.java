package wordSearch.spaceFinder;

import wordSearch.crossword.PlacementResults;
import wordSearch.puzzle.Grid;
import wordSearch.puzzle.Word;

import java.util.Random;

public class VerticalCrosswordSpaceFinder extends SpaceFinder {
    private Random generate = new Random();
    private int firstX;
    private int firstY;
    private int maxStartY;

    @Override
    public PlacementResults scanForWordSpace(Word word, Grid grid, int[] xy) {
        boolean inBounds = true;
        int x = xy[0];
        int y = xy[1];
        while (inBounds) { //this loops through every column
            for (int i = 0; i < word.length(); i++) {    //this loop checks through one column
                if (word.getChar(i).equals(grid.getBlock(x, y))) {
                    if (!wordSpaceOccupied(y - i, x, word, grid)) {
                        PlacementResults results = new PlacementResults(true, x, y - i);
                        return results;
                    }
                }
            }
            x++;
            if (isBlank(x, y, grid) || x >= grid.getWidth()) {
                inBounds = false;
            }
        }
        return null;
    }

    @Override
    boolean wordSpaceOccupied(int wordStartY, int testX, Word word, Grid grid) {
        if (!beforeAndAfterWordClean(testX, wordStartY, word, grid)) {
            return true;
        }
        for (int testY = wordStartY; testY < wordStartY + word.length(); testY++) { //check each letter in word for this 1 word space
            String currentLetterInWord = word.getChar(testY - wordStartY);
            if (hasLetter(testX, testY, currentLetterInWord, grid)) {
                return true;
            }
            if (!xPerimeterClean(testX, testY, currentLetterInWord, grid)) {
                return true;
            }
        }
        return false;
    }

    private boolean beforeAndAfterWordClean(int x, int y, Word word, Grid grid) {
        if (isBlank(x, y - 1, grid) && isBlank(x, y + word.length(), grid)) {
            return true;
        }
        return false;
    }

    private boolean xPerimeterClean(int x, int y, String letter, Grid grid) {
        if (!grid.getBlock(x, y).equals(letter) && isBlank(x + 1, y, grid) && isBlank(x - 1, y, grid)) {
            return true;
        }
        if (grid.getBlock(x, y).equals(letter)) {
            return true;
        }
        return false;
    }

    @Override
    public void assignInitialValues(Grid grid, Word word, int[] xy) {
        firstX = xy[0];
        firstY = xy[1];
//        maxStartY = grid.getHeight() - word.length();
//        firstX = generate.nextInt(grid.getWidth());
//        firstY = generate.nextInt(maxStartY);
    }

    @Override
    public void addWord(Word word, Grid grid, int x, int y) {
        for (int i = y, j = 0; i < y + word.length(); i++, j++) {
            grid.setBlock(x, i, word.getChar(j));
        }
    }

}
