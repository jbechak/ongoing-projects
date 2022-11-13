package com.zupple.dao;

import com.zupple.FileHandler;
import com.zupple.model.Crossword;
import com.zupple.model.WordSearch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JdbcCrosswordDao implements CrosswordDao {

    private final JdbcTemplate jdbcTemplate;
    private final FileHandler fileHandler = new FileHandler();
    private final String PATH_PREFIX = "crossword-puzzles/cw";
    private final String DOWN = "Down";
    private final String ACROSS = "Across";

    public JdbcCrosswordDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Crossword> getAll(int userId) {
        List<Crossword> crosswords = new ArrayList<>();

        String sql = "select crossword_id, title, description, difficulty, width, height, " +
                "genre, instructions, grid_path, html_path\n" +
                "from crossword where user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            Crossword crossword = mapRowToCrossword(results);
            crossword = getWordClues(crossword);
            crosswords.add(crossword);
        }
        return crosswords;
    }

    @Override
    public Crossword getCrossword(int crosswordId) {

        String sql = "select crossword_id, title, description, difficulty, width, height, " +
                "genre, instructions, grid_path, html_path\n" +
                "from crossword where crossword_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, crosswordId);

        if (results.next()) {
            Crossword crossword = mapRowToCrossword(results);
            crossword = getWordClues(crossword);
            return crossword;
        }
        return null;
    }

    @Override
    public Crossword createCrossword(Crossword crossword) {
        String sql = "insert into crossword (title, description, difficulty, " +
                "width, height, genre, instructions, grid_path)" +
                "values (?, ?, ?, ?,  ?, ?, ?, ?) returning crossword_id;";

        String gridPath = fileHandler.saveAsGrid(crossword.getGridString(), PATH_PREFIX);

        Integer crosswordId = jdbcTemplate.queryForObject(sql, Integer.class,
                crossword.getTitle(),
                crossword.getDescription(),
                crossword.getDifficulty(),
                crossword.getWidth(),
                crossword.getHeight(),
                crossword.getGenre(),
                crossword.getInstructions(),
                gridPath);

//        crossword.setCrosswordId(crosswordId);

        createCrosswordWordClue(crossword.getWordClues(), crossword.getDownClueList(), DOWN, crosswordId);
        createCrosswordWordClue(crossword.getWordClues(), crossword.getAcrossClueList(), ACROSS, crosswordId);
        return getCrossword(crosswordId);
    }

    @Override
    public Crossword updateCrossword(Crossword crossword) {
        fileHandler.saveGrid(crossword.getGridString(), crossword.getGridPath());
        String sql = "update crossword" +
                "set title = ? description = ?, difficulty = ?, " +
                "width = ?, height = ?, genre = ?, instructions = ?, grid_path = ? " +
                "where crossword_id = ?;";

        jdbcTemplate.update(sql,
                crossword.getTitle(),
                crossword.getDescription(),
                crossword.getDifficulty(),
                crossword.getWidth(),
                crossword.getHeight(),
                crossword.getGenre(),
                crossword.getInstructions(),
                crossword.getGridPath(),
                crossword.getCrosswordId());

        return getCrossword(crossword.getCrosswordId());
    }

    private Crossword mapRowToCrossword(SqlRowSet results) {
        Crossword crossword = new Crossword(results.getString("title"));
        crossword.setCrosswordId(results.getInt("crossword_id"));
        crossword.setDescription(results.getString("description"));
        crossword.setDifficulty(results.getString("difficulty"));
        crossword.setWidth(results.getInt("width"));
        crossword.setHeight(results.getInt("height"));
        crossword.setGenre(results.getString("genre"));
        crossword.setInstructions(results.getString("instructions"));



        String gridPath = results.getString("grid_path");
        String gridString = fileHandler.getGridFromFile(gridPath);
        crossword.setGridString(gridString);

        return crossword;
    }

    private Crossword getWordClues(Crossword crossword) {
        List<String> downClueList = new ArrayList<>();
        List<String> acrossClueList = new ArrayList<>();
        Map<String, String> wordClues = new HashMap<>();

        int crosswordId = crossword.getCrosswordId();

        String sql = "select clue_direction, clue_number, word, clue " +
                "from crossword_wordclue " +
                "where crossword_id = ? " +
                "order by clue_number;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, crosswordId);

        while (results.next()) {
            String clue = results.getString("clue");
            if (results.getString("clue_direction").equals(DOWN)) {
                downClueList.add(results.getString("clue_number") + ". " + clue);
            }
            if (results.getString("clue_direction").equals(ACROSS)) {
                acrossClueList.add(results.getString("clue_number") + ". " + clue);
            }
            wordClues.put(results.getString("word"), clue);
        }
//        Collections.sort(downClueList);
//        Collections.sort(acrossClueList);

        crossword.setWordClues(wordClues);
        crossword.setDownClueList(downClueList);
        crossword.setAcrossClueList(acrossClueList);

        return crossword;
    }

    private void createCrosswordWordClue(Map<String, String> wordClues, List<String> clueList,
                                          String direction, int crosswordId) {
        for (String clue : clueList) {
            String[] clueArray = clue.split("\\.");
            int clueNumber = 0;
            try {
                clueNumber = Integer.parseInt(clueArray[0]);
            } catch (NumberFormatException e) {
                System.out.println("Number Format Exception");
            }
            String thisClue = clueArray[1].trim();
            for (Map.Entry<String, String> wordClue : wordClues.entrySet()) {
                if (wordClue.getValue().equals(thisClue)) {
                    String sql = "insert into crossword_wordclue (crossword_id, clue_direction, " +
                            "clue_number, word, clue) " +
                            "values (?, ?, ?, ?, ?);";
                    jdbcTemplate.update(sql, crosswordId, direction, clueNumber,
                            wordClue.getKey(), wordClue.getValue());
                    break;
                }
            }
        }
    }
}
