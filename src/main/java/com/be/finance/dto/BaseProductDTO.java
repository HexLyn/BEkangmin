package com.be.finance.dto;

public class BaseProductDTO {
    private String dclsMonth;   // 공시 제출월
    private String finCoNo;     // 금융회사 코드
    private String finPrdtCd;   // 금융상품 코드
    private String korCoNm;     // 금융회사 명
    private String finPrdtNm;   // 금융상품 명
    private String joinWay;     // 가입 방법
    private String mtrtInt;     // 만기 후 이자율
    private String spclCnd;     // 우대조건
    private int joinDeny;       // 가입 제한
    private String joinMember;  // 가입 대상
    private String etcNote;     // 기타 유의사항
    private String maxLimit;    // 최고한도
    private String dclsStrtDay; // 공시 시작일
    private String dclsEndDay;  // 공시 종료일

    public String getDclsMonth() {
        return dclsMonth;
    }

    public void setDclsMonth(String dclsMonth) {
        this.dclsMonth = dclsMonth;
    }

    public String getFinCoNo() {
        return finCoNo;
    }

    public void setFinCoNo(String finCoNo) {
        this.finCoNo = finCoNo;
    }

    public String getFinPrdtCd() {
        return finPrdtCd;
    }

    public void setFinPrdtCd(String finPrdtCd) {
        this.finPrdtCd = finPrdtCd;
    }

    public String getKorCoNm() {
        return korCoNm;
    }

    public void setKorCoNm(String korCoNm) {
        this.korCoNm = korCoNm;
    }

    public String getFinPrdtNm() {
        return finPrdtNm;
    }

    public void setFinPrdtNm(String finPrdtNm) {
        this.finPrdtNm = finPrdtNm;
    }

    public String getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(String joinWay) {
        this.joinWay = joinWay;
    }

    public String getMtrtInt() {
        return mtrtInt;
    }

    public void setMtrtInt(String mtrtInt) {
        this.mtrtInt = mtrtInt;
    }

    public String getSpclCnd() {
        return spclCnd;
    }

    public void setSpclCnd(String spclCnd) {
        this.spclCnd = spclCnd;
    }

    public int getJoinDeny() {
        return joinDeny;
    }

    public void setJoinDeny(int joinDeny) {
        this.joinDeny = joinDeny;
    }

    public String getJoinMember() {
        return joinMember;
    }

    public void setJoinMember(String joinMember) {
        this.joinMember = joinMember;
    }

    public String getEtcNote() {
        return etcNote;
    }

    public void setEtcNote(String etcNote) {
        this.etcNote = etcNote;
    }

    public String getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(String maxLimit) {
        this.maxLimit = maxLimit;
    }

    public String getDclsStrtDay() {
        return dclsStrtDay;
    }

    public void setDclsStrtDay(String dclsStrtDay) {
        this.dclsStrtDay = dclsStrtDay;
    }

    public String getDclsEndDay() {
        return dclsEndDay;
    }

    public void setDclsEndDay(String dclsEndDay) {
        this.dclsEndDay = dclsEndDay;
    }
}
