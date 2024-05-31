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

@Controller
@RequiredArgsConstructor
@RequestMapping("/alpaca/aipica")
public class CrudController {

    private final WordService wordService;
    private final WordRepository wordRepository;

    @PostMapping(path="/addword")
    public String addwordAction(
            WordRequest wordRequest
    ){
        System.out.println("ADD WORD!");
        System.out.println(wordRequest);
        wordService.postWord(wordRequest);
        return "redirect:/alpaca/aipica/addword";
    }

    @PostMapping(path="/bookmark/update/{id}")
    public String updateTeam (
            @PathVariable Long id,
            WordRequest wordRequest
    ) {

        Optional<WordEntity> o = wordService.findById(id);
        WordEntity wordEntity = o.get();
        System.out.println("UPDATE");
        System.out.println(wordRequest);
        WordEntity wordEntity1 = wordService.update(wordRequest, wordEntity);
        return "redirect:/alpaca/aipica/wordlist";
    }

    @GetMapping(path="/findAllByUserName/{user_name}")
    public List<WordEntity> findAllByUserName(
            @PathVariable String user_name
    ){
        return wordService.findAllByUserName(user_name);
    }
}
