package com.langapp.word.repository;

import com.langapp.word.entity.Word;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {
    @Override
    @EntityGraph(attributePaths = { "translations" })
    <S extends Word> List<S> findAll(Example<S> example);
}
