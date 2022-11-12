package com.zupple.dao;

import com.zupple.model.Crossword;
import com.zupple.model.WordSearch;

import java.util.List;

public interface CrosswordDao {

    List<WordSearch> getAll();

    Crossword getWordSearch(int crosswordId);

    Crossword createCrossword(Crossword crossword);

    Crossword updateCrossword(Crossword crossword);
}
