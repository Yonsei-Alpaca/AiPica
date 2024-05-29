package com.alpaca.AiPica;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alpaca/aipica")
public class MainScreenController {
    @GetMapping(path="/")
    public String index(Model model){
        return "index";
    }
}
