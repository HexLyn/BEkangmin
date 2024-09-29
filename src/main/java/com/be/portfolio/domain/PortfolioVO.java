package com.be.portfolio.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioVO {
    private int portfolioId;
    private Date creationDate;
    private int total;
    private int expectedReturn;
    private int riskLevel;
    private String portfolioName;
    private int userNum;

    private List<PortfolioItemVO> portfolioItems = new ArrayList<>();
}
