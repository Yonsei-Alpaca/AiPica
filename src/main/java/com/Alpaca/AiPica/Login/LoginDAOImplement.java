package com.Alpaca.AiPica.Login;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImplement implements LoginDAO {
    public LoginDAOImplement() {
    }

    // 사용자 목록 가져옴
    public List<LoginDTO> userList(SqlSessionTemplate session) {
        return session.selectList("UserMapper.allUsers");
    }
}
