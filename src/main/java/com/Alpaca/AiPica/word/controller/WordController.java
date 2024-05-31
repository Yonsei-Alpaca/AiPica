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

@Controller
@RequiredArgsConstructor
@RequestMapping("/alpaca/aipica")
public class WordController {

    private final WordService wordService;

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
