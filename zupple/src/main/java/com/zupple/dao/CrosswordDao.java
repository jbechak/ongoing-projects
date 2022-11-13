package com.zupple.dao;

import com.zupple.model.Crossword;
import com.zupple.model.WordSearch;

import java.util.List;

public interface CrosswordDao {

    List<Crossword> getAll(int userId);

    Crossword getCrossword(int crosswordId);

    Crossword createCrossword(Crossword crossword);

    Crossword updateCrossword(Crossword crossword);
}
