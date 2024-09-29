package com.be.portfolio.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioPortionDto {
    private int totalSaving;
    private int totalBond;
    private int totalFund;
    private int totalStock;
}
