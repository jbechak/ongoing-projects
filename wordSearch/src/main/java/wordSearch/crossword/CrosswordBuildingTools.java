package wordSearch.crossword;

import wordSearch.HtmlPrintEncoder;
import wordSearch.main.UserInterface;
import wordSearch.puzzle.Grid;
import wordSearch.puzzle.Puzzle;
import wordSearch.puzzle.Word;
import wordSearch.puzzle.WordList;
import wordSearch.spaceFinder.*;

import java.util.*;

public class CrosswordBuildingTools {

    private UserInterface myInterface = new UserInterface();
    private SpaceFinder spaceFinder = new SpaceFinder();
    private HorizontalCrosswordSpaceFinder horizontalSpaceFinder = new HorizontalCrosswordSpaceFinder();
    private VerticalCrosswordSpaceFinder verticalSpaceFinder = new VerticalCrosswordSpaceFinder();
    HtmlPrintEncoder htmlEncoder = new HtmlPrintEncoder();
    CrosswordFileHandler fileHandler = new CrosswordFileHandler();
    private final int MAX_WIDTH = 81;


    public boolean isDuplicate(String newWord, Puzzle puzzle) {
        for (String word : puzzle.getWordCollection()) {
            if (newWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public String createGrid(CrosswordPuzzle puzzle) {
        puzzle.populateWordList();
        puzzle.setWordCount(puzzle.getWordList().size());
        CrosswordPuzzle thisPuzzle = buildCrosswordPuzzle(puzzle);

        printResults(thisPuzzle);
        for (Word word : thisPuzzle.getSortedWordList().getWords()) {
            System.out.println(word + ": " + word.getStartingX() + ", " +
                    word.getStartingY() + "\t" + word.getNumber() + " " + word.getDirection());
        }
        thisPuzzle.printWordClues();
        System.out.println(thisPuzzle.clueListsToString());
        String fileName = myInterface.enterSavePath();
        fileHandler.saveHtmlCrosswordPuzzle(fileName, thisPuzzle, htmlEncoder);
        return fileName;
    }

    public CrosswordPuzzle buildCrosswordPuzzle(CrosswordPuzzle puzzle) {
        List<CrosswordPuzzle> partialPuzzleList = new ArrayList<>();
//        List<CrosswordPuzzle> fullPuzzleList = new ArrayList<>();
        Map<Integer, CrosswordPuzzle> puzzleMap = new LinkedHashMap<>();
        CrosswordPuzzle thisPuzzle = new CrosswordPuzzle(puzzle.getTitle());
        WordList sortedWordList;
        boolean fullPuzzle = false;
        int smallestArea = 10000;
        for (int i = 0; i < 40; i++) {
            CrosswordPuzzle newPuzzle = createNewTrialPuzzle(puzzle);

            if (newPuzzle.getFinalWordList().size() == newPuzzle.getWordList().size()) {
                fullPuzzle = true;
                if (newPuzzle.getArea() < smallestArea) {
                    smallestArea = newPuzzle.getArea();

                    thisPuzzle = newPuzzle;
                    thisPuzzle.getFinalWordList().sortWordList();
                    thisPuzzle.setSortedWordList(thisPuzzle.getFinalWordList());
                    thisPuzzle.createClueLists();
                }
            } else {
                partialPuzzleList.add(newPuzzle);
            }
        }
        return thisPuzzle;
    }

    private CrosswordPuzzle createNewTrialPuzzle(CrosswordPuzzle puzzle) {
        CrosswordPuzzle newPuzzle = new CrosswordPuzzle(puzzle.getTitle(), puzzle.getWordClues(),
                puzzle.getWordCollection(), puzzle.getWordList(), puzzle.getWordCount());
        newPuzzle.getWordList().resetInGrid();
        generateCrosswordPuzzle(newPuzzle);
        newPuzzle.setWordCount(newPuzzle.getFinalWordList().size());
        trimPuzzle(newPuzzle);
        return newPuzzle;
    }

    private CrosswordPuzzle trimPuzzle(CrosswordPuzzle puzzle) {
        int topY = topRow(puzzle.getGrid());
        int leftX = leftColumn(puzzle.getGrid());
        int bottomY = bottomRow(puzzle.getGrid(), topY);
        int rightX = rightColumn(puzzle.getGrid(), leftX);
        int newWidth = rightX - leftX;
        int newHeight = bottomY - topY;

        Grid grid = new Grid(newWidth, newHeight);

        for (int y = topY, newY = 0; y < bottomY; y++, newY++){
            for (int x = leftX, newX = 0; x < rightX; x++, newX++) {
                grid.setBlock(newX, newY, puzzle.getGrid().getBlock(x, y));
            }
        }
        puzzle.setGrid(grid);

        for (Word word : puzzle.getFinalWordList().getWords()) {
            word.setStartingY(word.getStartingY() - topY);
            word.setStartingX(word.getStartingX() - leftX);
        }
        return puzzle;
    }

    private int topRow(Grid grid) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (!spaceFinder.isBlank(x, y, grid)) {
                    return y;
                }
            }
        }
        return 0;
    }

    private int leftColumn(Grid grid) {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (!spaceFinder.isBlank(x, y, grid)) {
                    return x;
                }
            }
        }
        return 0;
    }

    private int bottomRow(Grid grid, int topY) {
        for (int y = topY; y < grid.getHeight(); y++) {
            boolean rowIsBlank = true;
            for (int x = 0; x < grid.getWidth(); x++) {
                if (!spaceFinder.isBlank(x, y, grid)) {
                    rowIsBlank = false;
                    break;
                }
            }
            if (rowIsBlank) {
                return y;
            }
        }
        return grid.getHeight();
    }

    private int rightColumn(Grid grid, int leftX) {
        for (int x = leftX; x < grid.getWidth(); x++) {
            boolean rowIsBlank = true;
            for (int y = 0; y < grid.getHeight(); y++) {
                if (!spaceFinder.isBlank(x, y, grid)) {
                    rowIsBlank = false;
                    break;
                }
            }
            if (rowIsBlank) {
                return x;
            }
        }
        return grid.getWidth();
    }

    private void printResults(CrosswordPuzzle puzzle) {
        System.out.println(puzzle);
    }

    public void generateCrosswordPuzzle(CrosswordPuzzle puzzle) {
        Collections.shuffle(puzzle.getWordList().getWords());

        PlacementResults results = setFirstWord(blanklessWord(puzzle, 0), puzzle.getGrid());
        logWordEntry(results, puzzle, 0, false);
        int[] xy = {results.getStartingX(), results.getStartingY()};
        boolean goingDown = true;

        for (int j = 0; j < 30; j++) {
            for (int i = 1; i < puzzle.getWordList().size(); i++) {
                Word currentWord = blanklessWord(puzzle, i);
                recursivelySetWord(puzzle, currentWord, i, goingDown, xy);
                uncheckAllWords(puzzle.getWordList());
                goingDown = !goingDown;
            }
        }
    }

    private void recursivelySetWord(CrosswordPuzzle puzzle, Word currentWord, int i, boolean goingDown, int[] xy) {
        if (!puzzle.getWordList().getWord(i).isInGrid()) {
            PlacementResults results = placeWord(currentWord, puzzle.getGrid(), goingDown, xy);

            if (results != null) {
                puzzle = logWordEntry(results, puzzle, i, goingDown);
                xy = setXY(results.getStartingX(), results.getStartingY());

            } else {
                for (Word word : puzzle.getWordList().getWords()) {
                    if (!word.equals(puzzle.getWordList().getWord(i)) && !word.isChecked() && !puzzle.getWordList().getWord(i).isInGrid()) {
                        xy = setXY(word.getStartingX(), word.getStartingY());
                        word.setChecked(true);
                        recursivelySetWord(puzzle, currentWord, i, goingDown, xy);
                    }
                }
            }
        }
    }

    private void uncheckAllWords(WordList wordList) {
        for (Word word : wordList.getWords()) {
            word.setChecked(false);
        }
    }

    private int[] setXY(int x, int y) {
        return new int[] { x, y };
    }

    private CrosswordPuzzle logWordEntry(PlacementResults results, CrosswordPuzzle puzzle, int i, boolean goingDown) {
        puzzle.getWordList().getWord(i).setInGrid(true);
        puzzle.getWordList().getWord(i).setStartingX(results.getStartingX());
        puzzle.getWordList().getWord(i).setStartingY(results.getStartingY());
        if (goingDown) {
            puzzle.getWordList().getWord(i).setDirection("down");
        } else {
            puzzle.getWordList().getWord(i).setDirection("across");
        }
        puzzle.getFinalWordList().add(puzzle.getWordList().getWord(i));
        return puzzle;
    }



    private Word blanklessWord(CrosswordPuzzle puzzle, int index) {
        Word newWord =new Word(puzzle.getWordList().getWord(index).getLetterArray());
        newWord.withoutSpace();
        return newWord;
    }

    public PlacementResults setFirstWord(Word word, Grid grid) {
        int startingX = grid.getWidth() / 2 - word.length() / 2;
        int startingY = grid.getHeight() / 2;
        horizontalSpaceFinder.addWord(word, grid, startingX, startingY);
        PlacementResults placementResults = new PlacementResults(true, startingX, startingY);
        return placementResults;
    }

    private PlacementResults placeWord(Word word, Grid grid, boolean goingDown, int[] xy) {
        if (!goingDown) {
            return horizontalSpaceFinder.writeWordInEmptySpace(word, grid, xy);
        }
        else {
            return verticalSpaceFinder.writeWordInEmptySpace(word, grid, xy);
        }
    }

    private void printUnusedWords(Puzzle puzzle) {
        myInterface.unusedWords();
        int charactersInLine = 0;
        for (Word word : puzzle.getWordList().getWords()) {
            if (!puzzle.getFinalWordList().getWords().contains(word)) {
                if (charactersInLine + word.length() >= MAX_WIDTH) {
                    myInterface.printBlankLine();
                    charactersInLine = 0;
                }
                myInterface.printUnusedWord(word);
                charactersInLine += (word.length() + 2);
            }
        }
    }
}
