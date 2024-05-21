package com.Alpaca.AiPica.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    LoginService service;

    public LoginController() {
    }

    @PostMapping({"/login"})
    // 클라이언트로부터 받은 로그인 정보 확인, 세션에 로그인 정보 저장
    public boolean Login(@RequestBody LoginDTO dto, HttpServletRequest request) {
        System.out.println("***Login Controller***");
        System.out.println(" input ID: " + dto.getID());
        System.out.println(" input PW: " + dto.getPW());
        LoginDTO loginUser = this.service.checkLogin(dto);
        if (loginUser == null) {
            System.out.println(" Login failed");
            return false;
        } else {
            System.out.println(" Login success! Getting session...");
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser.getID());
            System.out.println(" Login completed!:" + session.getAttribute("loginUser"));
            return true;
        }
    }

    @PostMapping({"/logout"})
    // 세션 무효화
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

    }

    @PostMapping({"/check-session"})
    // 세션에 저장된 로그인 사용자 정보 반환
    public String checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? session.getAttribute("loginUser").toString() : null;
    }
}
