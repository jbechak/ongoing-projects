package com.zupple.controller;

import com.zupple.dao.SudokuDao;
import com.zupple.dto.SudokoDto;
import com.zupple.dto.WordSearchDto;
import com.zupple.model.Sudoku;
import com.zupple.model.WordSearch;
import com.zupple.sudoku.BlockGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    @Autowired
    private SudokuDao dao;

    //private BlockGenerator blockGenerator = new BlockGenerator();

    @GetMapping()
    public List<Sudoku> getAll() {
        int userId = 1;
        return dao.getAll(userId);
    }

    @GetMapping("/{id}")
    public Sudoku getSudoku(@PathVariable int id) {
        return dao.getSudoku(id);
    }

    @PostMapping("")
    public Sudoku createSudoku(@RequestBody SudokoDto dto) {
        Sudoku sudoku = new Sudoku();
        int difficulty = dto.getDifficulty();
        sudoku.setDifficulty(difficulty);
        BlockGenerator blockGenerator = new BlockGenerator();
        sudoku.setGridString(blockGenerator.createBoard(difficulty));
        sudoku.createTitle();
        return dao.createSudoku(sudoku);
    }

    @PutMapping("/{id}/update")
    public Sudoku updateSudoku(@PathVariable int id, @RequestBody Sudoku sudoku) {

        return dao.updateSudoku(sudoku);
    }



}
