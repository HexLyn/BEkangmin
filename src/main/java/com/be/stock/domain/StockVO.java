package com.be.stock.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockVO {
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
}
