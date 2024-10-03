package com.be.portfolio.dto.res;

import com.be.portfolio.domain.PortfolioVO;
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
public class PortfolioResDto {
    private int portfolioId;
    private String portfolioName;
    private Date creationDate;
    private int total;
    private int expectedReturn;
    private int riskLevel;
    private int memberNum;

    private List<PortfolioItemResDto> portfolioItems;
    private PortfolioPortionDto portion;

    public static PortfolioResDto of(PortfolioVO vo) {
        return vo == null ? null : PortfolioResDto.builder()
                .portfolioId(vo.getPortfolioId())
                .creationDate(vo.getCreationDate())
                .total(vo.getTotal())
                .expectedReturn(vo.getExpectedReturn())
                .riskLevel(vo.getRiskLevel())
                .portfolioName(vo.getPortfolioName())
                .memberNum(vo.getMemberNum())
                .build();
    }

    public PortfolioVO toVo() {
        return PortfolioVO.builder()
                .creationDate(creationDate)
                .total(total)
                .expectedReturn(expectedReturn)
                .riskLevel(riskLevel)
                .portfolioName(portfolioName)
                .memberNum(memberNum)
                .build();
    }
}
