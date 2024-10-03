package com.be.portfolio.dto.req;

import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.res.PortfolioResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioReqDto {
    private String portfolioName;
    private int total;
    private int expectedReturn;
    private int riskLevel;
    private int memberNum;

    private List<PortfolioItemReqDto> portfolioItems;

    public static PortfolioReqDto of(PortfolioResDto resDto) {
        return resDto == null ? null : PortfolioReqDto.builder()
                .total(resDto.getTotal())
                .expectedReturn(resDto.getExpectedReturn())
                .riskLevel(resDto.getRiskLevel())
                .portfolioName(resDto.getPortfolioName())
                .memberNum(resDto.getMemberNum())
                .build();
    }

    public PortfolioVO toVo() {
        return PortfolioVO.builder()
                .total(total)
                .expectedReturn(expectedReturn)
                .riskLevel(riskLevel)
                .portfolioName(portfolioName)
                .memberNum(memberNum)
                .build();
    }
}
