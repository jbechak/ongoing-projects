package wordSearch.crossword;

import wordSearch.puzzle.Word;

import java.util.Comparator;

public class SortByStartingY implements Comparator<Word> {

    @Override
    public int compare(Word a, Word b)
    {
        return a.getStartingY() - b.getStartingY();
    }
}
