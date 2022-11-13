package com.zupple.dao;

import com.zupple.FileHandler;
import com.zupple.model.Sudoku;
import com.zupple.model.WordSearch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSudokuDao implements SudokuDao {
    private final JdbcTemplate jdbcTemplate;
    private FileHandler fileHandler = new FileHandler();
    private final String PATH_PREFIX = "sudoku-puzzles/sd";

    public JdbcSudokuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sudoku> getAll(int userId) {
        List<Sudoku> sudokus = new ArrayList<>();

        String sql = "select sudoku_id, title, difficulty, " +
                "instructions, grid_path, html_path\n" +
                "from sudoku where user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while (results.next()) {
            sudokus.add(mapRowToSudoku(results));
        }
        return sudokus;
    }

    @Override
    public Sudoku getSudoku(int sudokuId) {
        String sql = "select sudoku_id, title, difficulty, " +
                "instructions, grid_path, html_path\n" +
                "from sudoku where sudoku_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sudokuId);

        if (results.next()) {
            return mapRowToSudoku(results);
        }
        return null;
    }

    @Override
    public Sudoku createSudoku(Sudoku sudoku) {
        String sql = "insert into sudoku (title, difficulty, " +
                "instructions, grid_path)" +
                "values (?, ?, ?, ?) returning sudoku_id;";

        String gridPath = fileHandler.saveAsGrid(sudoku.getGridString(), PATH_PREFIX);

        Integer sudokuId = jdbcTemplate.queryForObject(sql, Integer.class,
                sudoku.getTitle(),
                sudoku.getDifficulty(),
                sudoku.getInstructions(),
                gridPath);

        return getSudoku(sudokuId);
    }

    @Override
    public Sudoku updateSudoku(Sudoku sudoku) {

        String sql = "select grid_path from sudoku where sudoku_id = ?;";
        String gridPath = jdbcTemplate.queryForObject(sql, String.class, sudoku.getSudokuId());
        fileHandler.saveGrid(sudoku.getGridString(), gridPath);

        sql = "update sudoku " +
                "set title = ?, difficulty = ?, instructions = ?, grid_path = ? " +
                "where sudoku_id = ?;";

        jdbcTemplate.update(sql,
                sudoku.getTitle(),
                sudoku.getDifficulty(),
                sudoku.getInstructions(),
                gridPath,
                sudoku.getSudokuId());

        return getSudoku(sudoku.getSudokuId());
    }

    private Sudoku mapRowToSudoku(SqlRowSet result) {
        Sudoku sudoku = new Sudoku();
        sudoku.setSudokuId(result.getInt("sudoku_id"));
        sudoku.setTitle(result.getString("title"));
        sudoku.setDifficulty(result.getInt("difficulty"));
        sudoku.setInstructions(result.getString("instructions"));

        String gridPath = result.getString("grid_path");
        String gridString = fileHandler.getGridFromFile(gridPath);
        sudoku.setGridString(gridString);

        return sudoku;
    }
}
