package com.Alpaca.AiPica.controller;

import com.Alpaca.AiPica.dao.WordDao;
import com.Alpaca.AiPica.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordDao wordDao;

    @GetMapping
    public List<Word> getAllWords() {
        return wordDao.findAll();
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Word> addWord(@RequestParam("word") String word,
                                        @RequestParam("meaning") String meaning,
                                        @RequestParam(value = "memo", required = false) String memo,
                                        @RequestParam(value = "pronunciation", required = false) String pronunciation,
                                        @RequestParam(value = "groupName", required = false) String groupName,
                                        @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        Word newWord = new Word();
        newWord.setWord(word);
        newWord.setMeaning(meaning);
        newWord.setMemo(memo);
        newWord.setPronunciation(pronunciation);
        newWord.setGroupName(groupName != null ? groupName : "그룹 미지정");

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                newWord.setImage(imageFile.getBytes());
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        Word savedWord = wordDao.save(newWord);
        return ResponseEntity.ok(savedWord);
    }
}
