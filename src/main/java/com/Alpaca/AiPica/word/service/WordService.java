package com.alpaca.AiPica.word.service;

import com.alpaca.AiPica.word.entity.WordEntity;
import com.alpaca.AiPica.word.repository.WordRepository;
import com.alpaca.AiPica.word.dto.WordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {
    private final WordRepository wordRepository;

    public WordEntity postWord(
        WordRequest wordRequest
    ){
        var entity = WordEntity.builder()
                .userName(wordRequest.getUserName())
                .word(wordRequest.getWord())
                .meaning(wordRequest.getMeaning())
                .memo(wordRequest.getMemo())
                .groupTag(wordRequest.getGroupTag())
                .build()
                ;
        return wordRepository.save(entity);
    }

    public List<WordEntity> findAllByUserName(String name){
        return wordRepository.findAllByUserName(name);
    }
}
