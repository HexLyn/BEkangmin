package com.be.finance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BondProductVO {
    private int productId; // 상품ID 외래키 설정
    private String basDt; // 기준일자
    private String crno; // 법인등록번호
    private String scrsItmsKcd; // 유가증권종목코드
    private String isinCd;               // ISIN코드
    private String scrsItmsKcdNm;        // 유가증권종목코드명
    private String bondIsurNm; // 채권발행인명
    private String isinCdNm;             // ISIN코드명
    private String bondIssuDt; // 채권발행일자
    private Long bondIssuAmt;      // 채권발행금액
    private String bondIssuCurCd;        // 채권발행통화코드
    private String bondIssuCurCdNm;      // 채권발행통화코드명
    private String bondExprDt;           // 채권만기일자
    private Long bondPymtAmt;      // 채권납입금액
    private char irtChngDcd;             // 금리변동구분코드
    private String irtChngDcdNm;         // 금리변동구분코드명
    private BigDecimal bondSrfcInrt;     // 채권금리
    private char bondIntTcd;             // 채권이자형구분코드
    private String bondIntTcdNm;         // 채권이자형구분코드명
    private String intPayCyclCtt;        // 이자지급주기내용
    private String nxtmCopnDt;            // 차기표일자
    private String rbVopnDt;             // 직전이표일자
    private String kbpScrsItmsKcdNm;    // 한국신용평가유가증권종목종류코드명
    private String niceScrsItmsKcdNm;    //NICE평가정보유가증권종목종류코드명
    private String fnScrsItmsKcdNm;     //FN유가증권종목종류코드명
    private int riskLevel;            // 위험도
    private int hit;                     // 조회수
}
