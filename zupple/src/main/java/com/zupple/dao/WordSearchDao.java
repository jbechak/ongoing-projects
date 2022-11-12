package com.zupple.dao;

import com.zupple.model.WordSearch;
import com.zupple.puzzle.Puzzle;

import java.util.List;

public interface WordSearchDao {

    List<WordSearch> getAll(int userId);

    WordSearch getWordSearch(int wordSearchId);

    WordSearch createWordSearch(WordSearch wordSearch);

    WordSearch updateWordSearch(WordSearch wordSearch);


//    public List<Puzzle> sortPuzzlesByDifficulty();
//    public List<Puzzle> sortPuzzlesByWordCount();
//    public List<Puzzle> filterPuzzlesBy(String category);
}
