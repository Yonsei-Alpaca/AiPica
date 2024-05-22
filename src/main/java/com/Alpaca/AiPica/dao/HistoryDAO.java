package com.Alpaca.AiPica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HistoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getHistoryData() {
        String sql = "SELECT log_in_date, COUNT(user_id) AS user_count FROM history GROUP BY log_in_date ORDER BY log_in_date";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        // 데이터 확인을 위한 출력
        for (Map<String, Object> row : results) {
            System.out.println(row);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("labels", results.stream().map(row -> row.get("log_in_date")).toArray());
        response.put("datasets", new Object[]{
                new HashMap<String, Object>() {{
                    put("label", "User Activity");
                    put("data", results.stream().map(row -> row.get("user_count")).toArray());
                    put("borderColor", "rgb(75, 192, 192)");
                    put("tension", 0.1);
                }}
        });

        return response;
    }
}

