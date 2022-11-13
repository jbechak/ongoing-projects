package com.zupple.controller;

import com.zupple.crossword.CrosswordBuildingTools;
import com.zupple.crossword.CrosswordPuzzle;
import com.zupple.dao.CrosswordDao;
import com.zupple.dto.CrosswordDto;
import com.zupple.model.Crossword;
import com.zupple.puzzle.Puzzle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/crossword")
public class CrosswordController {

    @Autowired
    private CrosswordDao dao;

    private CrosswordBuildingTools buildingTools = new CrosswordBuildingTools();

    @GetMapping()
    public List<Crossword> getAll() {
        int userId = 1;
        return dao.getAll(userId);
    }

    @GetMapping("/{id}")
    public Crossword getCrossword(@PathVariable int id) {
        return dao.getCrossword(id);
    }

    @PostMapping("")
    public Crossword createCrossword(@RequestBody CrosswordDto dto) {
        CrosswordPuzzle puzzle = new CrosswordPuzzle(dto.getTitle());
        puzzle.setWordClues(dto.getWordClues());
        puzzle.populateWordList();
        Crossword crossword = buildingTools.createGrid(puzzle);
        crossword = dao.createCrossword(crossword);
        return crossword;
    }

    @PutMapping("/{id}/update")
    public Crossword updateCrossword(@PathVariable int crosswordId, @RequestBody CrosswordDto dto) {
        CrosswordPuzzle puzzle = new CrosswordPuzzle(dto.getTitle());
        puzzle.setWordClues(dto.getWordClues());
        puzzle.populateWordList();
        Crossword crossword = buildingTools.createGrid(puzzle);
        crossword.setCrosswordId(crosswordId);
        crossword = dao.updateCrossword(crossword);
        return crossword;
    }
}
