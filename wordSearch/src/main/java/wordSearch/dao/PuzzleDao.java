package wordSearch.dao;

import wordSearch.puzzle.Puzzle;

import java.util.List;

public interface PuzzleDao {

    public Integer createNewPuzzle(Puzzle puzzle);

    public List<Puzzle> getAllPuzzles();

    public Puzzle getPuzzle(int puzzleId);

    public List<Puzzle> sortPuzzlesByDifficulty();

    public List<Puzzle> sortPuzzlesByWordCount();

    public List<Puzzle> filterPuzzlesBy(String category);
}
