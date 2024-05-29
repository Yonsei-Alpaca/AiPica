package com.alpaca.AiPica.word.controller;

import com.alpaca.AiPica.word.entity.WordEntity;
import com.alpaca.AiPica.word.dto.WordRequest;
import com.alpaca.AiPica.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/alpaca/aipica")
public class CrudController {

    private final WordService wordService;

    @PostMapping(path="/addword")
    public String addwordAction(
            WordRequest wordRequest
    ){
        System.out.println("ADD WORD!");
        System.out.println(wordRequest);
        wordService.postWord(wordRequest);
        return "redirect:/alpaca/aipica/addword";
    }

    @GetMapping(path="/findAllByUserName/{user_name}")
    public List<WordEntity> findAllByUserName(
            @PathVariable String user_name
    ){
        return wordService.findAllByUserName(user_name);
    }
}
