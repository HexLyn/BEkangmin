package com.be.stock.repository;

import com.be.stock.domain.StockVO;
import com.be.stock.mapper.StockMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockRepository {

    private final SqlSessionTemplate my;

    @Autowired
    public StockRepository(SqlSessionTemplate my) {
        this.my = my;
    }

    // 주식 데이터를 DB에서 조회하는 메서드
    public List<StockVO> findAll() {
        return my.getMapper(StockMapper.class).findAll();  // 전체 데이터 조회
    }

    public int insert(StockVO stockVO) {
        return my.getMapper(StockMapper.class).insert(stockVO);  // DB에 저장
    }

    // stockCode 또는 stockName으로 검색
    public List<StockVO> searchByCodeOrName(String searchTerm) {
        return my.getMapper(StockMapper.class).searchStock(searchTerm);
    }
}
