package com.langapp.word.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WordRequestDTO {
    @NotBlank(message = "word type cannot be blank")
    private String wordType;

    @NotBlank(message = "term cannot be blank")
    private String term;

    @NotNull(message = "translation ids cannot be null")
    private List<Integer> translationIds;

    private String gender;
    private String plural;
}
