package com.alpaca.AiPica.word.controller;

import com.alpaca.AiPica.word.dto.WordRequest;
import com.alpaca.AiPica.word.service.WordService;
import com.alpaca.AiPica.word.entity.WordEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.alpaca.AiPica.word.repository.WordRepository;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alpaca/aipica")
public class WordController {

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
    public String bookmarkUpdate (
            @PathVariable Long id,
            WordRequest wordRequest
    ) {

        Optional<WordEntity> o = wordService.findById(id);
        WordEntity wordEntity = o.get();
        System.out.println("UPDATE");
        System.out.println(wordRequest);
        WordEntity wordEntity1 = wordService.update(wordRequest, wordEntity);
        return "redirect:/alpaca/aipica/bookmark";
    }

    @PostMapping(path="/wordlist/update/{id}")
    public String wordlistUpdate (
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

    @GetMapping(path="/wordlist")
    public String wordlist(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if("anonymousUser".equals(authentication.getName())){
            return "redirect:/alpaca/aipica/login";
        }

        List<WordEntity> words = wordService.findAllByUserName(authentication.getName());
        System.out.println(authentication.getName());
        model.addAttribute("words", words);
        model.addAttribute("username", authentication.getName());
        return "wordlist";
    }
    @GetMapping(path="/bookmark")
    public String bookmarkPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if("anonymousUser".equals(authentication.getName())){
            return "redirect:/alpaca/aipica/login";
        }

        List<WordEntity> words = wordService.findAllByUserName(authentication.getName());
        System.out.println(authentication.getName());
        model.addAttribute("words", words);
        model.addAttribute("username", authentication.getName());
        return "bookmark";
    }

    @GetMapping(path="/addword")
    public String addword(Model model, Principal principal){
        if(principal == null){
            return "redirect:/alpaca/aipica/login";
        }
        model.addAttribute("wordRequest", new WordRequest());
        model.addAttribute("username", principal.getName());
        return "addword";
    }
}
