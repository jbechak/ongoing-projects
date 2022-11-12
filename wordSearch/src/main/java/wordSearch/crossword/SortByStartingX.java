package wordSearch.crossword;

import wordSearch.puzzle.Word;

import java.util.Comparator;

public class SortByStartingX implements Comparator<Word> {

    @Override
    public int compare(Word a, Word b)
    {
        return a.getStartingX() - b.getStartingX();
    }
}
