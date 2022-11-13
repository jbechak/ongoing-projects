package com.zupple.dao;

import com.zupple.FileHandler;
import com.zupple.model.WordSearch;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcWordSearchDao implements WordSearchDao {

    private final JdbcTemplate jdbcTemplate;
    private final FileHandler fileHandler = new FileHandler();
    private final String PATH_PREFIX = "wordsearch-puzzles/ws";

    public JdbcWordSearchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WordSearch> getAll(int userId) {
        List<WordSearch> wordSearches = new ArrayList<>();

        String sql = "select wordsearch_id, title, description, difficulty, width, height, " +
                "genre, instructions, grid_path, html_path\n" +
                "from wordsearch where user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while (results.next()) {
            wordSearches.add(mapRowToWordSearch(results));
        }
        return wordSearches;
    }

    @Override
    public WordSearch getWordSearch(int wordSearchId) {
        String sql = "select wordsearch_id, title, description, difficulty, width, height, " +
                "genre, instructions, grid_path, html_path\n" +
                "from wordsearch where wordsearch_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wordSearchId);

        if (results.next()) {
            return mapRowToWordSearch(results);
        }
        return null;
    }

    @Override
    public WordSearch createWordSearch(WordSearch wordSearch) {
        String sql = "insert into wordsearch (title, description, difficulty, " +
                "width, height, genre, instructions, grid_path)" +
                "values (?, ?, ?, ?,  ?, ?, ?, ?) returning wordsearch_id;";

        String gridPath = fileHandler.saveAsGrid(wordSearch.getGridString(), PATH_PREFIX);

        Integer wordSearchId = jdbcTemplate.queryForObject(sql, Integer.class,
                wordSearch.getTitle(),
                wordSearch.getDescription(),
                wordSearch.getDifficulty(),
                wordSearch.getWidth(),
                wordSearch.getHeight(),
                wordSearch.getGenre(),
                wordSearch.getInstructions(),
                gridPath);

        createWordCollection(wordSearchId, wordSearch.getWordCollection());

        return getWordSearch(wordSearchId);
    }

    @Override
    public WordSearch updateWordSearch(WordSearch wordSearch) {
        String sql = "select grid_path from wordsearch where wordsearch_id = ?;";
        String gridPath = jdbcTemplate.queryForObject(sql, String.class, wordSearch.getWordSearchId());
        fileHandler.saveGrid(wordSearch.getGridString(), gridPath);

        sql = "update wordsearch " +
                "set title = ?, description = ?, difficulty = ?, " +
                "width = ?, height = ?, genre = ?, instructions = ?, grid_path = ? " +
                "where wordsearch_id = ?;";

        jdbcTemplate.update(sql,
                wordSearch.getTitle(),
                wordSearch.getDescription(),
                wordSearch.getDifficulty(),
                wordSearch.getWidth(),
                wordSearch.getHeight(),
                wordSearch.getGenre(),
                wordSearch.getInstructions(),
                gridPath,
                wordSearch.getWordSearchId());

        updateWordCollection(wordSearch.getWordSearchId(), wordSearch.getWordCollection());

        return getWordSearch(wordSearch.getWordSearchId());
    }

    private WordSearch mapRowToWordSearch(SqlRowSet results) {
        WordSearch wordSearch = new WordSearch(results.getString("title"));
        wordSearch.setWordSearchId(results.getInt("wordsearch_id"));
        wordSearch.setDescription(results.getString("description"));
        wordSearch.setDifficulty(results.getString("difficulty"));
        wordSearch.setWidth(results.getInt("width"));
        wordSearch.setHeight(results.getInt("height"));
        wordSearch.setGenre(results.getString("genre"));
        wordSearch.setInstructions(results.getString("instructions"));

        String gridPath = results.getString("grid_path");
        String gridString = fileHandler.getGridFromFile(gridPath);
        wordSearch.setGridString(gridString);

        wordSearch.setWordCollection(getWordCollection(wordSearch.getWordSearchId()));

        return wordSearch;
    }

    private List<String> getWordCollection(int wordSearchId) {
        List<String> wordCollection = new ArrayList<>();
        String sql = "select word from wordsearch_word where wordsearch_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wordSearchId);

        while (results.next()) {
            wordCollection.add(results.getString("word"));
        }
        return wordCollection;
    }

    private void createWordCollection(int wordSearchId, List<String> wordCollection) {
        for (String word : wordCollection) {
            String sql = "insert into wordsearch_word (wordsearch_id, word) " +
                    "values (?, ?);";
            jdbcTemplate.update(sql, wordSearchId, word);
        }
    }

    private void updateWordCollection(int wordSearchId, List<String> wordCollection) {
        String sql = "delete from wordsearch_word where wordsearch_id = ?;";
        jdbcTemplate.update(sql, wordSearchId);
        createWordCollection(wordSearchId, wordCollection);
    }
}


