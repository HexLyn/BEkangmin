package com.be.finance.dto;

import java.math.BigDecimal;

public class OptionProductDTO {
    private String finPrdtCd;
    private int saveTrm;
    private BigDecimal intrRate;
    private BigDecimal intrRate2;
    private char intrRateType;
    private String intrRateTypeNm;
    private char rsrvType;
    private String rsrvTypeNm;

    public String getFinPrdtCd() {
        return finPrdtCd;
    }

    public void setFinPrdtCd(String finPrdtCd) {
        this.finPrdtCd = finPrdtCd;
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

    public String getRsrvTypeNm() {
        return rsrvTypeNm;
    }

    public void setRsrvTypeNm(String rsrvTypeNm) {
        this.rsrvTypeNm = rsrvTypeNm;
    }
}
