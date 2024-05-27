package com.Alpaca.AiPica.controller;

import com.Alpaca.AiPica.dao.MainScreenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MainScreenController {

    @Autowired
    private MainScreenDao mainScreenDao;

    @GetMapping("/mainScreen")
    public String showMainScreen(Model model) {
        List<Map<String, Object>> words = mainScreenDao.getAllWords();
        model.addAttribute("words", words);
        return "mainScreen";
    }
}
