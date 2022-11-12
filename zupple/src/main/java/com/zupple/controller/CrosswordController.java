package com.zupple.controller;

import com.zupple.crossword.CrosswordBuildingTools;
import com.zupple.crossword.CrosswordPuzzle;
import com.zupple.dto.CrosswordDto;
import com.zupple.puzzle.Puzzle;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/crossword")
public class CrosswordController {

    private CrosswordBuildingTools buildingTools = new CrosswordBuildingTools();

    @PostMapping("")
    public Puzzle createCrossword(@RequestBody CrosswordDto dto) {
        CrosswordPuzzle puzzle = new CrosswordPuzzle(dto.getTitle());
        puzzle.setWordClues(dto.getWordClues());
        puzzle.populateWordList();
        buildingTools.createGrid(puzzle);
        return puzzle;

    }
}
