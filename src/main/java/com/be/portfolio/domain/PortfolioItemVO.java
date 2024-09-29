package com.be.portfolio.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioItemVO {
    private int portfolioItemId;
    public int portfolioId;
    private int productId;
    private String stockCode;
    private int amount;
    private double expectedReturn;
    private String productType;
    private int dailyPrice;
}
