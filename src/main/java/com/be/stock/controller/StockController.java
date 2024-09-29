package com.be.stock.controller;

import com.be.stock.domain.StockVO;
import com.be.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    // 주식 데이터를 DB에 저장하는 fetch 메서드 (GET /api/stock/fetch)
    @GetMapping("/fetch")
    public String fetchStockData() {
        stockService.fetchStock();  // 주식 데이터를 가져와서 DB에 저장
        return "Stock data fetched successfully";
    }

    // 전체 주식 데이터를 조회하는 API
    @GetMapping("/all")
    public List<StockVO> getAllStocks() {
        return stockService.getAllStocks();
    }

    // stockCode 또는 stockName으로 검색, 엔드포인트
    @GetMapping("/searchStock")
    public List<StockVO> searchStock(@RequestParam("searchTerm") String searchTerm) {
        // 주식 데이터를 검색해서 반환
        return stockService.searchStock(searchTerm);

    }
}
