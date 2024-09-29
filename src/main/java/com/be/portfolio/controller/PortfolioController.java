package com.be.portfolio.controller;

//import com.be.cart.service.CartService;
import com.be.finance.service.FinanceService;
import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.service.PortfolioService;
import com.be.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;
//    private final CartService cartService;
    private final StockService stockService;
    private final FinanceService financeService;

//    @GetMapping("/{userNum}")
//    public ResponseEntity<List<CartItemResDto>> getCartItems(@PathVariable Integer userNum) {
//        return ResponseEntity.ok(cartService.getCartList(userNum));
//    }

//    @GetMapping
//    public ResponseEntity<List<FinanceResDto>> getFinanceProduct(@RequestParam String query) {
//        return ResponseEntity.ok(financeService.get(query));
//    }

//    @GetMapping
//    public ResponseEntity<List<StockResDto>> getStocks(@RequestParam String query) {
//        return ResponseEntity.ok(stockService.get(query));
//    }

    @PostMapping
    public ResponseEntity<PortfolioResDto> createPortfolio(PortfolioReqDto portfolioReqDto, List<PortfolioItemReqDto> portfolioItems) {
        return ResponseEntity.ok(portfolioService.createPortfolio(portfolioReqDto, portfolioItems));
    }

    @GetMapping("/{portfolioID}")
    public ResponseEntity<PortfolioResDto> getPortfolio(@PathVariable int portfolioID) {
        return ResponseEntity.ok(portfolioService.getPortfolio(portfolioID));
    }

    @DeleteMapping("/{portfolioID}")
    public ResponseEntity<PortfolioResDto> deletePortfolio(@PathVariable Integer portfolioID) {
        return ResponseEntity.ok(portfolioService.deletePortfolio(portfolioID));
    }

    // 전략 패턴 선택?

    // 튜토리얼?
}

