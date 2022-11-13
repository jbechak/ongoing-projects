package com.zupple.dao;

import com.zupple.model.Sudoku;
import com.zupple.model.WordSearch;

import java.util.List;

public interface SudokuDao {

    List<Sudoku> getAll(int userId);

    Sudoku getSudoku(int sudokuId);

    Sudoku createSudoku(Sudoku sudoku);

    Sudoku updateSudoku(Sudoku sudoku);
}
