package com.Alpaca.AiPica.controller;

import com.Alpaca.AiPica.dao.HistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HistoryController {

    @Autowired
    private HistoryDAO historyDAO;

    @GetMapping("/history")
    public String getHistoryPage() {
        return "history"; // "history.html" 파일을 반환
    }

    @GetMapping("/history/data")
    @ResponseBody
    public Map<String, Object> getHistoryData() {
        return historyDAO.getHistoryData();
    }
}
