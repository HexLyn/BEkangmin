package com.be.portfolio.repository;

import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.mapper.PortfolioMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class portfolioRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public portfolioRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<PortfolioItemVO> getPortfolioItemList(int portfolioId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).getPortfolioItemList(portfolioId);
    }

    public PortfolioVO getPortfolio(int portfolioId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).getPortfolio(portfolioId);
    }

    public int insertPortfolio(PortfolioVO portfolioVO) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).insertPortfolio(portfolioVO);
    }

    public int insertPortfolioItem(PortfolioItemVO portfolioItemVO) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).insertPortfolioItem(portfolioItemVO);
    }

    public int updatePortfolio(int portfolioId, PortfolioVO portfolioVO) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).updatePortfolio(portfolioVO);
    }

    public int deletePortfolio(int portfolioId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).deletePortfolio(portfolioId);
    }

    public int deletePortfolioItem(int portfolioItemId) {
        return sqlSessionTemplate.getMapper(PortfolioMapper.class).deletePortfolioItem(portfolioItemId);
    }
}
