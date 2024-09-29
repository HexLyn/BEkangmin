package com.be.finance.dto;

import java.math.BigDecimal;

public class SavingProductRateDTO {
    private int productId;       // 외래키: Product ID
    private int saveTrm;         // 저축 기간
    private BigDecimal intrRate; // 저축 금리
    private BigDecimal intrRate2;// 최고 우대금리
    private char intrRateType;
    private String intrRateTypeNm;
    private char rsrvType;
    private char rsrvTypeNm;

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

    public char getRsrvType() {
        return rsrvType;
    }

    public void setRsrvType(char rsrvType) {
        this.rsrvType = rsrvType;
    }

    public char getRsrvTypeNm() {
        return rsrvTypeNm;
    }

    public void setRsrvTypeNm(char rsrvTypeNm) {
        this.rsrvTypeNm = rsrvTypeNm;
    }
}
