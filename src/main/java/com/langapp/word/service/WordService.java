package com.langapp.word.service;

import com.langapp.word.dto.WordRequestDTO;
import com.langapp.word.entity.Translation;
import com.langapp.word.entity.Word;
import com.langapp.word.exception.WordNotFoundException;
import com.langapp.word.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {
    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
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
        Word word = mapWordDtoToEntity(wordDTO);

        return this.wordRepository.save(word);
    }

    public Word updateWord(int id, WordRequestDTO wordRequestDTO) {
        Word wordToUpdate = wordRepository.findById(id).orElseThrow(() -> new WordNotFoundException(id));

        Word word = mapWordDtoToEntity(wordRequestDTO);

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

    private Word mapWordDtoToEntity(WordRequestDTO wordDTO) {
        Word word = new Word();

        word.setWordType(wordDTO.getWordType());
        word.setTerm(wordDTO.getTerm());
        word.setGender(wordDTO.getGender());
        word.setPlural(wordDTO.getPlural());

        List<Translation> translations = wordDTO.getTranslationIds().stream()
                .map(translationId -> {
                    Translation translation = new Translation();
                    translation.setId(translationId);

                    return translation;
                })
                .collect(Collectors.toList());

        word.setTranslations(translations);

        return word;
    }
}
