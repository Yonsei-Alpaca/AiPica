package com.Alpaca.AiPica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/menuB")
    public String menuB() {
        return "menuB";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @GetMapping("/bookMark")
    public String bookMark() {
        return "bookMark";
    }

    @GetMapping("/addWord")
    public String addWord() {
        return "addWord";
    }
}

