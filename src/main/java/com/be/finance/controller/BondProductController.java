package com.be.finance.controller;


import com.be.finance.domain.BondProductVO;
import com.be.finance.domain.FundProductVO;
import com.be.finance.service.BondProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bond")
@CrossOrigin(origins = "http://localhost:5173")
public class BondProductController {

    @Autowired
    private BondProductService bondProductService;

    // 채권 데이터 DB 저장
    @GetMapping("/fetch-save")
    @ResponseBody
    public String fetchAndSaveBondProducts() {
        try {
            bondProductService.fetchAndSaveBondProducts();
            return "채권 데이터 저장 성공"; // 성공
        } catch (Exception e) {
            e.printStackTrace();
            return "채권 데이터 저장 중 오류 발생"; // 오류
        }
    }

    // 채권 데이터 조회 API
    @GetMapping("/list")
    public ResponseEntity<List<BondProductVO>> getBondProductsList() {
        try {
            List<BondProductVO> bondProducts = bondProductService.getBondProductsList();
            System.out.println("성공");
            return ResponseEntity.ok(bondProducts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // 채권 상품 검색
    @GetMapping("/search")
    public ResponseEntity<List<BondProductVO>> searchBondProducts(@RequestParam String keyword) {
        List<BondProductVO> results = bondProductService.searchBondProducts(keyword);
        return ResponseEntity.ok(results);
    }

}
