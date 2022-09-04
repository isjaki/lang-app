package com.langapp.word.dto;

import java.util.List;

public class WordRequestDTO {
    private String wordType;
    private String gender;
    private String term;
    private String plural;
    private List<Integer> translationIds;

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
