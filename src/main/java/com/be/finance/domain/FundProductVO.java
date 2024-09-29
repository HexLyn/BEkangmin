package com.be.finance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundProductVO {
    private int productId;
    private String companyNm;
    private String productNm;
    private BigDecimal yield1;
    private BigDecimal yield3;
    private BigDecimal yield6;
    private BigDecimal yield12;
    private int riskLevel;
    private String fundType;
    private BigDecimal advancedFee;
    private BigDecimal totalPayoffRate;
    private int hit;

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public BigDecimal getTotalPayoffRate() {
        return totalPayoffRate;
    }

    public void setTotalPayoffRate(BigDecimal totalPayoffRate) {
        this.totalPayoffRate = totalPayoffRate;
    }

    public BigDecimal getAdvancedFee() {
        return advancedFee;
    }

    public void setAdvancedFee(BigDecimal advancedFee) {
        this.advancedFee = advancedFee;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public BigDecimal getYield12() {
        return yield12;
    }

    public void setYield12(BigDecimal yield12) {
        this.yield12 = yield12;
    }

    public BigDecimal getYield6() {
        return yield6;
    }

    public void setYield6(BigDecimal yield6) {
        this.yield6 = yield6;
    }

    public BigDecimal getYield3() {
        return yield3;
    }

    public void setYield3(BigDecimal yield3) {
        this.yield3 = yield3;
    }

    public BigDecimal getYield1() {
        return yield1;
    }

    public void setYield1(BigDecimal yield1) {
        this.yield1 = yield1;
    }

    public String getProductNm() {
        return productNm;
    }

    public void setProductNm(String productNm) {
        this.productNm = productNm;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
