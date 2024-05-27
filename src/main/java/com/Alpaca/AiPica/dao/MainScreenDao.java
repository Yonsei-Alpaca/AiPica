package com.Alpaca.AiPica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MainScreenDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllWords() {
        String sql = "SELECT word, meaning, group_name FROM words";
        return jdbcTemplate.queryForList(sql);
    }
}
