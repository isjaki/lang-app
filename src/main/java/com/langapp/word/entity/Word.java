package com.langapp.word.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
