package com.be.portfolio.controller;

import com.be.cart.service.CartService;
import com.be.finance.service.FinanceService;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.service.PortfolioService;
import com.be.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Log4j
public class PortfolioController {
    private final PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<List<PortfolioResDto>> getPortfolioList(HttpServletRequest request) {
        return ResponseEntity.ok(portfolioService.getPortfolioList(1L));
    }

    @GetMapping("/{portfolioID}")
    public ResponseEntity<PortfolioResDto> getPortfolio(@PathVariable int portfolioID) {
        return ResponseEntity.ok(portfolioService.getPortfolio(portfolioID));
    }

    @PostMapping
    public ResponseEntity<PortfolioResDto> createPortfolio(@RequestBody @Valid PortfolioReqDto portfolioReqDto, HttpServletRequest request) {
        return ResponseEntity.ok(portfolioService.createPortfolio(portfolioReqDto));
    }

    @DeleteMapping("/{portfolioID}")
    public ResponseEntity<PortfolioResDto> deletePortfolio(@PathVariable Integer portfolioID) {
        return ResponseEntity.ok(portfolioService.deletePortfolio(portfolioID));
    }
}

