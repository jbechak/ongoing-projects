package wordSearch.spaceFinder;

import wordSearch.puzzle.Grid;
import wordSearch.puzzle.Word;

import java.util.Random;

public class VerticalSpaceFinder extends SpaceFinder {
    private Random generate = new Random();
    private int firstX;
    private int firstY;
    private int maxStartY;

    @Override
    public int[] scanForWordSpace(Word word, Grid grid) {
        assignInitialValues(grid, word);

        for (int i = 0; i < grid.getWidth(); i++) {           //this loops through every column
            int testX = setTestXY(i, firstX, grid.getWidth());

            for (int j = 0; j < maxStartY; j++) {    //this loop checks through one column
                boolean blockTaken = false;
                int wordStartY = setWordStart(j, firstY, maxStartY);
                blockTaken = wordSpaceOccupied(wordStartY, testX, word, grid);

                if (!blockTaken) { //space for word found!!!
                    return foundLocation(testX, wordStartY);
                }
            }
        }
        return foundLocation(-1, -1);
    }

    @Override
    public void assignInitialValues(Grid grid, Word word) {
        maxStartY = grid.getHeight() - word.length();
        firstX = generate.nextInt(grid.getWidth());
        firstY = generate.nextInt(maxStartY);
    }

    @Override
    public void addWord(Word word, Grid grid, int x, int y) {
        for (int i = y, j = 0; i < y + word.length(); i++, j++) {
            grid.setBlock(x, i, word.getChar(j));
        }
    }


}
