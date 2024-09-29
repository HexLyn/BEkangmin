package com.be.portfolio.mapper;

import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.domain.PortfolioVO;

import java.util.List;

public interface PortfolioMapper {
    List<PortfolioItemVO> getPortfolioItemList(int portfolioId);
    PortfolioVO getPortfolio(int portfolioId);
    int insertPortfolio(PortfolioVO portfolioVO);
    int insertPortfolioItem(PortfolioItemVO portfolioItemVO);
    List<Integer> getAllIds();
    int updatePortfolio(PortfolioVO portfolioVO);
    int deletePortfolio(int portfolioId);
    int deletePortfolioItem(int portfolioItemId);
}
