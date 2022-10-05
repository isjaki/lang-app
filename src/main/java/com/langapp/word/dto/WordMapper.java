package com.langapp.word.dto;

import com.langapp.word.entity.Translation;
import com.langapp.word.entity.Word;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordMapper {
    public Word mapWordRequestDtoToEntity(WordRequestDTO wordRequestDTO) {
        Word word = new Word();

        word.setWordType(wordRequestDTO.getWordType());
        word.setTerm(wordRequestDTO.getTerm());
        word.setGender(wordRequestDTO.getGender());
        word.setPlural(wordRequestDTO.getPlural());

        List<Translation> translations = wordRequestDTO.getTranslationIds().stream()
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
