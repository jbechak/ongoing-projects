package wordSearch.dto;

import wordSearch.puzzle.Grid;
import wordSearch.puzzle.WordList;

import java.util.ArrayList;
import java.util.List;

public class WordSearchDto {
    private String title;
    private int width;
    private int height;
    private int wordDirections;
    private List<String> wordCollection = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getWordDirections() {
        return wordDirections;
    }

    public void setWordDirections(int wordDirections) {
        this.wordDirections = wordDirections;
    }

    public List<String> getWordCollection() {
        return wordCollection;
    }

    public void setWordCollection(List<String> wordCollection) {
        this.wordCollection = wordCollection;
    }
}
