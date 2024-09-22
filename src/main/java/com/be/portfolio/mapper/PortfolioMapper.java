package com.be.portfolio.mapper;

import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioResDto;

import java.util.List;

public interface PortfolioMapper {
    List<PortfolioItemResDto> getPortfolioItemList(int portfolioId);
    PortfolioResDto getPortfolio(int portfolioId);
    void createPortfolio(PortfolioReqDto portfolioReqDto);
    void createPortfolioItem(PortfolioItemReqDto portfolioItemReqDto);
    int deletePortfolio(int portfolioId);
    int deletePortfolioItem(int portfolioItemId);
}
