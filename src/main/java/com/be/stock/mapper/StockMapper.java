package com.be.stock.mapper;


import com.be.stock.domain.StockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {
    int insert(StockVO stockVO);

    List<StockVO> searchStock(String searchTerm);
    List<StockVO> findAll();
}