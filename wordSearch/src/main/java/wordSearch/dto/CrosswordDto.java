package wordSearch.dto;

import wordSearch.puzzle.Grid;
import wordSearch.puzzle.WordList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrosswordDto {
    private String title;
    private Map<String, String> wordClues;


    public Map<String, String> getWordClues() {
        return wordClues;
    }

    public void setWordClues(Map<String, String> wordClues) {
        this.wordClues = wordClues;
    }
}
