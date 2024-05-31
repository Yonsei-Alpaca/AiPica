package com.alpaca.AiPica.word.service;

import com.alpaca.AiPica.word.entity.WordEntity;
import com.alpaca.AiPica.word.repository.WordRepository;
import com.alpaca.AiPica.word.dto.WordRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .imageUrl(wordRequest.getImageUrl())
                .example(wordRequest.getExample())
                .groupTag(wordRequest.getGroupTag())
                .bookmark(wordRequest.getBookmark())
                .build()
                ;
        return wordRepository.save(entity);
    }

    @Transactional
    public WordEntity update (
            WordRequest wordRequest,
            WordEntity wordEntity
    ) {
        wordEntity.setUserName(wordRequest.getUserName());
        wordEntity.setWord(wordRequest.getWord());
        wordEntity.setMeaning(wordRequest.getMeaning());
        wordEntity.setMemo(wordRequest.getMemo());
        wordEntity.setImageUrl(wordRequest.getImageUrl());
        wordEntity.setExample(wordRequest.getExample());
        wordEntity.setGroupTag(wordRequest.getGroupTag());
        wordEntity.setBookmark(wordRequest.getBookmark());

        return wordRepository.save(wordEntity);
    }


    public List<WordEntity> findAllByUserName(String name){
        return wordRepository.findAllByUserName(name);
    }
    public Optional<WordEntity> findById(Long id){
        return wordRepository.findById(id);
    }
}
