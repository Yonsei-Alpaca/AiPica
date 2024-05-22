package com.Alpaca.AiPica.controller;

import com.Alpaca.AiPica.dao.UserDao;
import com.Alpaca.AiPica.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        // Save user to the database
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model) {
        Optional<User> optionalUser = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (optionalUser.isPresent()) {
            session.setAttribute("email", user.getEmail());
            return "index"; // 로그인 성공 시 홈 페이지로 리다이렉션
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; // 로그인 실패 시 로그인 페이지로 다시 이동
        }
    }

}