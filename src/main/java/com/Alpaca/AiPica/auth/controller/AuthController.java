package com.alpaca.AiPica.auth.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.alpaca.AiPica.auth.dto.UserRequest;
import com.alpaca.AiPica.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/alpaca/aipica")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping(path = "/reg")
    public String userRegister(
            @Valid
            @ModelAttribute("userRequest") UserRequest userRequest, BindingResult bindingResult,
            Model model
    ) {

        if (!userRequest.getPassword1().equals(userRequest.getPassword2())) {

            bindingResult.rejectValue("password2", "PasswordInCorrect", "비밀번호가 서로 다릅니다.");

            return "signup";
        }

        try {
            authService.userRegister(userRequest);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.error("\n=== ID 중복===\n{}", e.getMessage());
            bindingResult.reject("signupFailed", "이미 존재하는 ID 입니다.");
            return "signup";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup";
        }

        return "redirect:/alpaca/aipica/login";
    }

    // 회원가입화면
    @GetMapping(path = "/signup")
    public String signUp(
            UserRequest userRequest
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!("anonymousUser".equals(authentication.getName()))){
            return "redirect:/alpaca/aipica/wordlist";
        }

        return "signup";
    }

    // 로그인 화면
    @GetMapping(path = "/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!("anonymousUser".equals(authentication.getName()))){
            return "redirect:/alpaca/aipica/wordlist";
        }
        return "login";
    }
}