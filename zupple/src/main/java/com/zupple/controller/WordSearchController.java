package com.zupple.controller;

import com.zupple.dto.TestDto;
import com.zupple.dto.WordSearchDto;
import com.zupple.main.BuildingTools;
import com.zupple.model.WordSearch;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.http.HttpResponse;


@CrossOrigin
@RestController
@RequestMapping("/wordsearch")
public class WordSearchController {

    private BuildingTools buildingTools = new BuildingTools();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public WordSearch createWordSearch(@RequestBody WordSearchDto dto) {
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
//        return puzzle.getGrid().toString();

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
