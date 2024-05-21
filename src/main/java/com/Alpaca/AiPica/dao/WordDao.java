package com.Alpaca.AiPica.dao;

import com.Alpaca.AiPica.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordDao extends JpaRepository<Word, Integer> {
    // 필요한 경우 사용자 정의 쿼리를 추가할 수 있습니다.
}
