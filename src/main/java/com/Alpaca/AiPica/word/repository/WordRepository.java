package com.alpaca.AiPica.word.repository;

import com.alpaca.AiPica.word.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<WordEntity, Long> {
    List<WordEntity> findAllByUserName(String userName);
}
