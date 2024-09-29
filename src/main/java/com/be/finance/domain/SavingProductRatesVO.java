package com.be.finance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingProductRatesVO {
    private int productId;       // 외래키: Product ID
    private int saveTrm;         // 저축 기간
    private char intrRateType; // 저축 금리 유형
    private String intrRateTypeNm; // 저축 금리 유형명
    private BigDecimal intrRate;  // 저축 금리
    private BigDecimal intrRate2; // 최고 우대금리
    private String rsrvType;      // 적립 유형
    private String rsrvTypeNm;    // 적립 유형명

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSaveTrm() {
        return saveTrm;
    }

    public void setSaveTrm(int saveTrm) {
        this.saveTrm = saveTrm;
    }

    public char getIntrRateType() {
        return intrRateType;
    }

    public void setIntrRateType(char intrRateType) {
        this.intrRateType = intrRateType;
    }

    public String getIntrRateTypeNm() {
        return intrRateTypeNm;
    }

    public void setIntrRateTypeNm(String intrRateTypeNm) {
        this.intrRateTypeNm = intrRateTypeNm;
    }

    public BigDecimal getIntrRate() {
        return intrRate;
    }

    public void setIntrRate(BigDecimal intrRate) {
        this.intrRate = intrRate;
    }

    public BigDecimal getIntrRate2() {
        return intrRate2;
    }

    public void setIntrRate2(BigDecimal intrRate2) {
        this.intrRate2 = intrRate2;
    }

    public String getRsrvType() {
        return rsrvType;
    }

    public void setRsrvType(String rsrvType) {
        this.rsrvType = rsrvType;
    }

    public String getRsrvTypeNm() {
        return rsrvTypeNm;
    }

    public void setRsrvTypeNm(String rsrvTypeNm) {
        this.rsrvTypeNm = rsrvTypeNm;
    }
}
