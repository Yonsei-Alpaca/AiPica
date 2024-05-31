package com.alpaca.AiPica.word.controller;

import com.alpaca.AiPica.word.entity.WordEntity;
import com.alpaca.AiPica.word.dto.WordRequest;
import com.alpaca.AiPica.word.repository.WordRepository;
import com.alpaca.AiPica.word.service.WordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alpaca/aipica")
public class CrudController {

    private final WordService wordService;
    private final WordRepository wordRepository;



    @GetMapping(path="/findAllByUserName/{user_name}")
    public List<WordEntity> findAllByUserName(
            @PathVariable String user_name
    ){
        return wordService.findAllByUserName(user_name);
    }
}
