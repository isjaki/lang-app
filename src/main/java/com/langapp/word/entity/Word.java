package com.langapp.word.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Integer -> Long ?
    private Integer id;

    private String term;

    private String plural;

    private String gender;

    private String wordType;

    @ManyToMany
    @JoinTable(
            name = "word_translation",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "translation_id"))
    private List<Translation> translations;

    public Word() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", term='" + term + '\'' +
                ", plural='" + plural + '\'' +
                ", gender='" + gender + '\'' +
                ", wordType='" + wordType + '\'' +
                ", translations=" + translations +
                '}';
    }
}
