package com.Alpaca.AiPica.Login;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;

public interface LoginDAO {
    List<LoginDTO> userList(SqlSessionTemplate session);
}
