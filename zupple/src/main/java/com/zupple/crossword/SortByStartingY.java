package com.zupple.crossword;

import com.zupple.puzzle.Word;
import java.util.Comparator;

public class SortByStartingY implements Comparator<Word> {

    @Override
    public int compare(Word a, Word b)
    {
        return a.getStartingY() - b.getStartingY();
    }
}
