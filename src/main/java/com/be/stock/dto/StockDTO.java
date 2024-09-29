package com.be.stock.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StockDTO {
    private String stockCode;
    private String stockName;
    private BigDecimal dailyPrice;
    private Integer field;
    private String itmsNm;
    private String mrktCtg;
    private Integer clpr;
    private Integer vs;
    private BigDecimal fltRt;
    private Integer mkp;
    private Integer hipr;
    private Integer lopr;
    private BigInteger trqu;
    private BigInteger trPrc;
    private BigInteger istgStCnt;
    private BigInteger mrktTotAmt;

    // Getters and Setters
    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public String getItmsNm() {
        return itmsNm;
    }

    public void setItmsNm(String itmsNm) {
        this.itmsNm = itmsNm;
    }

    public String getMrktCtg() {
        return mrktCtg;
    }

    public void setMrktCtg(String mrktCtg) {
        this.mrktCtg = mrktCtg;
    }

    public Integer getClpr() {
        return clpr;
    }

    public void setClpr(Integer clpr) {
        this.clpr = clpr;
    }

    public Integer getVs() {
        return vs;
    }

    public void setVs(Integer vs) {
        this.vs = vs;
    }

    public BigDecimal getFltRt() {
        return fltRt;
    }

    public void setFltRt(BigDecimal fltRt) {
        this.fltRt = fltRt;
    }

    public Integer getMkp() {
        return mkp;
    }

    public void setMkp(Integer mkp) {
        this.mkp = mkp;
    }

    public Integer getHipr() {
        return hipr;
    }

    public void setHipr(Integer hipr) {
        this.hipr = hipr;
    }

    public Integer getLopr() {
        return lopr;
    }

    public void setLopr(Integer lopr) {
        this.lopr = lopr;
    }

    public BigInteger getTrqu() {
        return trqu;
    }

    public void setTrqu(BigInteger trqu) {
        this.trqu = trqu;
    }

    public BigInteger getTrPrc() {
        return trPrc;
    }

    public void setTrPrc(BigInteger trPrc) {
        this.trPrc = trPrc;
    }

    public BigInteger getIstgStCnt() {
        return istgStCnt;
    }

    public void setIstgStCnt(BigInteger istgStCnt) {
        this.istgStCnt = istgStCnt;
    }

    public BigInteger getMrktTotAmt() {
        return mrktTotAmt;
    }

    public void setMrktTotAmt(BigInteger mrktTotAmt) {
        this.mrktTotAmt = mrktTotAmt;
    }
}
