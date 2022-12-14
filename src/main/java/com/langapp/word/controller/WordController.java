package com.langapp.word.controller;

import com.langapp.word.dto.WordRequestDTO;
import com.langapp.word.entity.Word;
import com.langapp.word.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class WordController {
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/words")
    public Iterable<Word> getWords(@RequestParam Optional<String> type,
                                   @RequestParam Optional<String> gender,
                                   @RequestParam Optional<String> term) {
        return wordService.getWordsByParam(
                type.orElse(null),
                gender.orElse(null),
                term.orElse(null));
    }

    @GetMapping("/words/{id}")
    public Word getWordById(@PathVariable int id) {
        return wordService.getWordById(id);
    }

    @PostMapping("/words")
    public Word createWord(@RequestBody @Valid WordRequestDTO word) {
        return wordService.createWord(word);
    }

    @PutMapping("/words/{id}")
    public Word updateWord(@PathVariable int id, @RequestBody @Valid WordRequestDTO wordRequestDTO) {
        return wordService.updateWord(id, wordRequestDTO);
    }

    @DeleteMapping("/words/{id}")
    public void deleteWordById(@PathVariable int id) {
        wordService.deleteWordById(id);
    }
}
