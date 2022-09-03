package com.langapp.word.controller;

import com.langapp.word.dto.WordDTO;
import com.langapp.word.entity.Word;
import com.langapp.word.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/words")
    public Word createWord(@RequestBody WordDTO word) {
        return wordService.saveWord(word);
    }

    @PutMapping("/words/{id}")
    public Word updateWord(@PathVariable int id, @RequestBody Word word) {
        return wordService.updateWord(id, word);
    }

    @DeleteMapping("/words/{id}")
    public void deleteWordById(@PathVariable int id) {
        wordService.deleteWordById(id);
    }
}
