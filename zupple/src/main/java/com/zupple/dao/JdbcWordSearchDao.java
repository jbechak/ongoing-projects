package com.zupple.dao;

import com.zupple.model.WordSearch;
import com.zupple.puzzle.Puzzle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class JdbcWordSearchDao implements WordSearchDao {

    private final JdbcTemplate jdbcTemplate;

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

        while(results.next()) {
            wordSearches.add(mapRowToWordSearch);
        }
        return wordSearches;
    }

    @Override
    public WordSearch getWordSearch(int wordSearchId) {
        return null;
    }

    @Override
    public WordSearch createWordSearch(WordSearch wordSearch) {
        return null;
    }

    @Override
    public WordSearch updateWordSearch(WordSearch wordSearch) {
        return null;
    }

    private WordSearch mapRowToWordSearch(SqlRowSet results) {
        WordSearch wordSearch = new WordSearch();
        wordSearch.setWordSearchId(results.getInt("wordsearch_id"));
        wordSearch.setDescription(results.getString("description"));
        wordSearch.setDifficulty(results.getString("difficulty"));
        wordSearch.setWidth(results.getInt("width"));
        wordSearch.setHeight(results.getInt("height"));
        wordSearch.setGenre(results.getString("genre"));
        wordSearch.setInstructions(results.getString("instructions"));

        String gridPath = results.getString("grid_path");
        String gridString = getGridFromFile(gridPath);
        wordSearch.setGridString(gridString);

        return wordSearch;
    }

    private String getGridFromFile(String filePathString) {
        String gridString = "";
        File sourceFile = new File(filePathString);
        try (Scanner fileScanner = new Scanner(sourceFile)) {
            while (fileScanner.hasNext()) {
                gridString += fileScanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        return gridString;

    }
}
