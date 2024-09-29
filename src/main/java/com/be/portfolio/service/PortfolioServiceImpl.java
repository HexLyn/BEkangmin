package com.be.portfolio.service;

import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioPortionDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    final private PortfolioMapper portfolioMapper;

    @Override
    public PortfolioResDto getPortfolio(int portfolioId) {
        PortfolioResDto resDto = PortfolioResDto.of(portfolioMapper.getPortfolio(portfolioId));
        return Optional.ofNullable(resDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<PortfolioItemResDto> getPortfolioItems(int portfolioId) {
        return portfolioMapper.getPortfolioItemList(portfolioId)
                .stream().map(PortfolioItemResDto::of).toList();
    }

    @Override
    public PortfolioResDto createPortfolio(PortfolioReqDto reqDto, List<PortfolioItemReqDto> portfolioItems) {
        reqDto.setPortfolioItems(portfolioItems);
        reqDto = calculatePortfolio(reqDto);
        reqDto.setPortfolioId(portfolioMapper.insertPortfolio(reqDto.toVo()));

        for(PortfolioItemReqDto portfolioItem : portfolioItems) {
            portfolioItem.setPortfolioId(reqDto.getPortfolioId());
            portfolioMapper.insertPortfolioItem(portfolioItem.toVo());
        }

        PortfolioResDto resDto = getPortfolio(reqDto.getPortfolioId());
        resDto.setPortion(calculatePortion(getPortfolioItems(resDto.getPortfolioId())));

        return resDto;
    }

    @Override
    public PortfolioResDto updatePortfolio(PortfolioVO portfolioVO) {
        portfolioMapper.updatePortfolio(portfolioVO);

        return getPortfolio(portfolioVO.getPortfolioId());
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAllPortfolio() {
        List<Integer> portfolioIds = new ArrayList<>(portfolioMapper.getAllIds());
        for(Integer portfolioId : portfolioIds) {
            PortfolioReqDto portfolio = PortfolioReqDto.of(getPortfolio(portfolioId));
            List<PortfolioItemReqDto> portfolioItems = getPortfolioItems(portfolioId)
                    .stream().map(PortfolioItemReqDto::of).toList();
            portfolio.setPortfolioItems(portfolioItems);
            PortfolioReqDto reqDto = calculatePortfolio(portfolio);
            updatePortfolio(reqDto.toVo());
        }
    }

    @Override
    public PortfolioResDto deletePortfolio(int id) {
        PortfolioResDto resDto = getPortfolio(id);

        // portfolioItems는 delete cascade 설정
        portfolioMapper.deletePortfolio(id);

        return resDto;
    }

    @Override
    public PortfolioReqDto calculatePortfolio(PortfolioReqDto dto) {

        // 파이썬 플라스크 서버 연결 + 계산 + 반환
        // portfolio의 expectedReturn, riskLevel, portfolioItem의 expectedReturn 반환
        dto.setTotal(100);
        dto.setExpectedReturn(10000);
        dto.setRiskLevel(14);
        //

        return dto;
    }

    @Override
    public PortfolioPortionDto calculatePortion(List<PortfolioItemResDto> portfolioItems) {
        PortfolioPortionDto dto = new PortfolioPortionDto();

        for(PortfolioItemResDto item : portfolioItems) {
            switch(item.getProductType() == null ? "stock" : item.getProductType()) {
                case "S" -> dto.setTotalSaving(dto.getTotalSaving() + item.getAmount());
                case "F" -> dto.setTotalFund(dto.getTotalFund() + item.getAmount());
                case "B" -> dto.setTotalBond(dto.getTotalBond() + item.getAmount());
                case "stock" -> dto.setTotalStock(dto.getTotalStock() + (item.getAmount() * item.getDailyPrice()));
            }
        }

        return dto;
    }
}
