package com.Alpaca.AiPica.Login;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplement implements LoginService {
    @Autowired
    LoginDAO dao;
    @Autowired
    SqlSessionTemplate session;

    public LoginServiceImplement() {
    }

    // 클라이언트로부터 전달받은 로그인 정보 사용하여 실제 로그인 확인
    public LoginDTO checkLogin(LoginDTO dto) {
        List<LoginDTO> userList = this.dao.userList(this.session);

        for(int i = 0; i < userList.size(); ++i) {
            if (((LoginDTO)userList.get(i)).getID().equals(dto.getID()) && ((LoginDTO)userList.get(i)).getPW().equals(dto.getPW())) {
                return dto;
            }
        }

        return null;
    }
}
