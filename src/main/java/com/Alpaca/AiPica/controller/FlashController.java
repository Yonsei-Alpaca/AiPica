package com.Alpaca.AiPica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlashController {

    @GetMapping("/flashcard")
    public String showFlashcardPage() {
        return "flashcard"; // 이 문자열은 HTML 파일의 이름입니다. 뷰 리졸버가 해당 HTML을 찾아서 반환합니다.
    }
}
