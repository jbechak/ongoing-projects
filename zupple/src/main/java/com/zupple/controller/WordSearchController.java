package com.zupple.controller;

import com.zupple.crossword.CrosswordPuzzle;
import com.zupple.dao.WordSearchDao;
import com.zupple.dto.CrosswordDto;
import com.zupple.dto.TestDto;
import com.zupple.dto.WordSearchDto;
import com.zupple.main.BuildingTools;
import com.zupple.model.Crossword;
import com.zupple.model.WordSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/wordsearch")
public class WordSearchController {

    @Autowired
    private WordSearchDao dao;

    private BuildingTools buildingTools = new BuildingTools();

    @GetMapping()
    public List<WordSearch> getAll() {
        int userId = 1;
        return dao.getAll(userId);
    }

    @GetMapping("/{id}")
    public WordSearch getWordsearch(@PathVariable int id) {
        return dao.getWordSearch(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public WordSearch createWordsearch(@RequestBody WordSearchDto dto) {
        WordSearch wordSearch = new WordSearch(dto.getTitle());
        wordSearch.setWidth(dto.getWidth());
        wordSearch.setHeight(dto.getHeight());
        wordSearch.setWordDirections(dto.getWordDirections());
        wordSearch.setWordCollection(dto.getWordCollection());
        try {
            buildingTools.createWordSearch(wordSearch);

        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY);
        }
        return dao.createWordSearch(wordSearch);
    }

    @PutMapping("/{id}/update")
    public WordSearch updateWordsearch(@PathVariable int id, @RequestBody WordSearchDto dto) {
        WordSearch wordSearch = new WordSearch(dto.getTitle());
        wordSearch.setWidth(dto.getWidth());
        wordSearch.setHeight(dto.getHeight());
        wordSearch.setWordDirections(dto.getWordDirections());
        wordSearch.setWordCollection(dto.getWordCollection());
        try {
            buildingTools.createWordSearch(wordSearch);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY);
        }
        wordSearch.setWordSearchId(id);
        dao.updateWordSearch(wordSearch);
        return wordSearch;
    }





//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("")
//    public TestDto test(@RequestBody TestDto dto) {
//        dto.setName(dto.getName() + 2);
//        dto.setPassword(dto.getPassword() + 2);
//        return dto;
//    }



}
