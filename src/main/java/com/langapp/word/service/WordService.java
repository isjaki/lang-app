package com.langapp.word.service;

import com.langapp.word.dto.WordMapper;
import com.langapp.word.dto.WordRequestDTO;
import com.langapp.word.entity.Word;
import com.langapp.word.exception.WordNotFoundException;
import com.langapp.word.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


@Service
public class WordService {
    private final WordRepository wordRepository;
    private final WordMapper wordMapper;

    @Autowired
    public WordService(WordRepository wordRepository, WordMapper wordMapper) {
        this.wordRepository = wordRepository;
        this.wordMapper = wordMapper;
    }

    public Word getWordById(int id) {
        return wordRepository.findById(id).orElseThrow(() -> new WordNotFoundException(id));
    }

    public Iterable<Word> getWordsByParam(String type, String gender, String term) {
        Word word = new Word();
        word.setWordType(type);
        word.setGender(gender);
        word.setTerm(term);

        return this.wordRepository.findAll(Example.of(word));
    }

    public Word createWord(WordRequestDTO wordDTO) {
        Word word = wordMapper.mapWordRequestDtoToEntity(wordDTO);

        return this.wordRepository.save(word);
    }

    public Word updateWord(int id, WordRequestDTO wordRequestDTO) {
        Word wordToUpdate = wordRepository.findById(id).orElseThrow(() -> new WordNotFoundException(id));

        Word word = wordMapper.mapWordRequestDtoToEntity(wordRequestDTO);

        wordToUpdate.setWordType(word.getWordType());
        wordToUpdate.setTerm(word.getTerm());
        wordToUpdate.setGender(word.getGender());
        wordToUpdate.setPlural(word.getPlural());
        wordToUpdate.setTranslations(word.getTranslations());

        return wordRepository.save(wordToUpdate);
    }

    public void deleteWordById(int id) {
        wordRepository.deleteById(id);
    }
}
