package com.be.finance.controller;

import com.be.finance.domain.SavingProductVO;
import com.be.finance.dto.SavingProductDTO;
import com.be.finance.service.SavingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/saving-products")
@CrossOrigin(origins = "http://localhost:5173")
public class SavingProductController {

    @Autowired
    private SavingProductService savingProductService;

    // 예금 api fetch해서 데이터 저장
    @GetMapping("/fetch-saveDeposit")
    public String fetchDepositProducts() {
        savingProductService.fetchAndSaveDepositProducts();
        return "예금 데이터 저장 완료";
    }

    // 적금 api fetch해서 데이터 저장
    @GetMapping("/fetch-saveSaving")
    public String fetchSavingProducts() {
        savingProductService.fetchAndSaveSavingProducts();
        return "적금 데이터 저장 완료";
    }

    // 예금 리스트 조회 API
    @GetMapping("/deposit")
    public Map<String, Object> getDepositProducts() {
        return savingProductService.getDepositProducts();
    }

    // 적금 리스트 조회 API
    @GetMapping("/saving")
    public Map<String, Object> getSavingProducts() {
        return savingProductService.getSavingProducts();
    }

    // 예금 상품 검색
    @GetMapping("/depositSearch")
    public Map<String, Object> searchDepositProducts(@RequestParam String keyword) {
        return savingProductService.searchDepositProducts(keyword);
    }

    // 적금 상품 검색
    @GetMapping("/savingSearch")
    public Map<String, Object> searchSavingProducts(@RequestParam String keyword) {
        return savingProductService.searchSavingProducts(keyword);
    }
}