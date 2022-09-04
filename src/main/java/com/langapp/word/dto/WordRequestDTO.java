package com.langapp.word.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class WordRequestDTO {
    @NotBlank(message = "word type cannot be blank")
    private String wordType;

    @NotBlank(message = "term cannot be blank")
    private String term;

    @NotNull(message = "translation ids cannot be null")
    private List<Integer> translationIds;

    private String gender;
    private String plural;

    public WordRequestDTO() {}

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public List<Integer> getTranslationIds() {
        return translationIds;
    }

    public void setTranslationIds(List<Integer> translationIds) {
        this.translationIds = translationIds;
    }
}
