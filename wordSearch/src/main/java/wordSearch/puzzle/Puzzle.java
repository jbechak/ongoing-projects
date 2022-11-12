package wordSearch.puzzle;

import wordSearch.HtmlEncoder;
import wordSearch.HtmlPrintEncoder;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private final int FULL_WIDTH = 81;
    private Grid grid;
    private int puzzleId;
    private String title;
    private String description;
    private int wordDirections = 1;
    private int wordCount;
    private int width;
    private int height;
    private String genre;
    private String instructions;
    private String creator = "Justin Bechak";
    private String filePath;
    private List<String> wordCollection = new ArrayList<>();
    private WordList wordList = new WordList();
    private WordList finalWordList = new WordList();
    private boolean isSaved = false;

    public Puzzle(String title) {
        this.title = title;
    }

    public Puzzle() {}

    public int getWordDirections() {
        return wordDirections;
    }

    public void setWordDirections(int wordDirections) {
        this.wordDirections = wordDirections;
    }

    public String getWordDirectionsString() {
        if (wordDirections == 1) {
            return "Words can go horizontally and vertically";
        }
        if (wordDirections == 2) {
            return "Words can go horizontally, vertically, and in 2 diagonal directions";
        }
        if (wordDirections == 3) {
            return "Words can go horizontally, vertically, diagonally, and backwards in all directions";
        }
        return "";
    }

    @Override
    public String toString() {
        String puzzleString = "\n" + tabToCenter(title) + "\n" + tabToCenter(getDifficulty()) + "\n\n" + instructions + "\n\n" +
                grid.toString() + "\n\n" + finalWordList.toString();

        return puzzleString;
    }

    public String toHtml(HtmlEncoder htmlEncoder) {
        String htmlPuzzle = htmlEncoder.htmlHeader() + htmlEncoder.htmlTitle(title, getDifficulty()) +
                htmlEncoder.htmlInstructions(instructions) + htmlEncoder.htmlGrid(grid) +
                htmlEncoder.htmlWordList(finalWordList);
        return htmlPuzzle;
    }

    private String tabToCenter(String word) {
        String space = "";
        int spaces = (FULL_WIDTH - word.length()) / 2;
        for (int i = 0; i < spaces; i++) {
            space += " ";
        }
        return space + word;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public List<String> getWordCollection() {
        return wordCollection;
    }

    public void setWordCollection(List<String> wordCollection) {
        this.wordCollection = wordCollection;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public void populateWordList(List<String> wordCollection) {
        wordList.populateFromStringList(wordCollection);
    }

    public WordList getWordList() {
        return wordList;
    }

    public void setFinalWordList(WordList finalWordList) {
        this.finalWordList = finalWordList;
    }

    public WordList getFinalWordList() {
        return finalWordList;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        if (getCredits() >= 8) {
            return "Level 5 - Very Difficult";
        }
        if (getCredits() >= 6) {
            return "Level 4 - Difficult";
        }
        if (getCredits() >= 3) {
            return "Level 3 - Medium";
        }
        if (getCredits() >= 1) {
            return "Level 2 - Easy";
        }
        return "Level 1 - Very Easy";
    }

    private int getCredits() {
        int credits = 0;
        credits += grid.remainingSpaces() / 50;
        credits += wordDirections - 1;
        credits += (wordCount - 1) / 10;
        return credits;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void incrementWordCount() {
        wordCount++;
    }

    public void decrementWordCount() {
        wordCount--;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getWsgFilePath() {
        return filePath + ".wsg";
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHtmlFilePath() {
        return filePath + ".html";
    }
}

