package com.zupple.dao;

import com.zupple.puzzle.Puzzle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPuzzleDao implements PuzzleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPuzzleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer createNewPuzzle(Puzzle puzzle) {
        String sql = "insert into word_search " +
                "(title, description, difficulty, " +
                "word_count, width, height, word_directions, genre, instructions, creator, " +
                "wsg_file_path, html_file_path) values (?,?,?, ?,?,?, ?,?,?, ?,?,?) " +
                "returning puzzle_id;";

        Integer puzzleId = jdbcTemplate.queryForObject(sql, Integer.class,
                puzzle.getTitle(),
                puzzle.getDescription(),
                puzzle.getDifficulty(),
                puzzle.getWordCount(),
                puzzle.getWidth(),
                puzzle.getHeight(),
                puzzle.getWordDirectionsString(),
                puzzle.getGenre(),
                puzzle.getInstructions(),
                puzzle.getCreator(),
                puzzle.getWsgFilePath(),
                puzzle.getHtmlFilePath());

        return puzzleId;
    }

    @Override
    public List<Puzzle> getAllPuzzles(){
        List<Puzzle> puzzles = new ArrayList<>();

        String sql = "select puzzle_id, title, description, difficulty, word_count, width, height, genre, instructions, creator, file_path\n" +
                "from word_search order by title, difficulty;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            puzzles.add(mapRowToPuzzle(results));
        }

        return puzzles;
    }

    public List<Puzzle> sortPuzzlesByDifficulty() {
        List<Puzzle> puzzles = new ArrayList<>();

        String sql = "select puzzle_id, title, description, difficulty, word_count, width, height, genre, instructions, creator, file_path " +
                "from word_search order by difficulty, title;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            puzzles.add(mapRowToPuzzle(results));
        }

        return puzzles;
    }

    public List<Puzzle> sortPuzzlesByWordCount() {
        List<Puzzle> puzzles = new ArrayList<>();

        String sql = "select puzzle_id, title, description, difficulty, word_count, width, height, genre, instructions, creator, file_path " +
                "from word_search order by word_count, title;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            puzzles.add(mapRowToPuzzle(results));
        }

        return puzzles;
    }

    public List<Puzzle> filterPuzzlesBy(String category) {
        List<Puzzle> puzzles = new ArrayList<>();

        String sql = "select puzzle_id, title, description, difficulty, word_count, width, height, genre, instructions, creator, file_path " +
                "from word_search where      order by title;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            puzzles.add(mapRowToPuzzle(results));
        }

        return puzzles;
    }

    @Override
    public Puzzle getPuzzle(int puzzleId) {
        Puzzle puzzle = new Puzzle();
        String sql = "select puzzle_id, title, description, " +
                "difficulty, word_count, width, height, genre, " +
                "instructions, creator, file_path from word_search where puzzle_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, puzzleId);

        if (results.next()) {
            puzzle = mapRowToPuzzle(results);
        }
        return puzzle;

    }



    private Puzzle mapRowToPuzzle(SqlRowSet results) {
        Puzzle newPuzzle = new Puzzle(results.getString("title"));
        newPuzzle.setPuzzleId(results.getInt("puzzle_id"));
        newPuzzle.setDescription(results.getString("description"));
        //newPuzzle.setDifficulty(results.getString("difficulty"));
        newPuzzle.setWordCount(results.getInt("word_count"));
        newPuzzle.setWidth(results.getInt("width"));
        newPuzzle.setHeight(results.getInt("height"));
        newPuzzle.setGenre(results.getString("genre"));
        newPuzzle.setInstructions(results.getString("instructions"));
        newPuzzle.setCreator(results.getString("creator"));
        newPuzzle.setFilePath(results.getString("file_path"));
        return newPuzzle;
    }
}
