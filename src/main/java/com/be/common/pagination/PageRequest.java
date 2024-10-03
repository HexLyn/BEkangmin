package com.be.common.pagination;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE) // private 레벨의 생성자(외부에서 접근 불가) -> 팩토리 메서드로 사용
public class PageRequest {
    private int page; // 요청 페이지
    private int amount; //한 페이지 당 데이터 건수

    // 기본 생성자에서 기본 페이지 1개, 기본 데이터 10개로 초기화
    public PageRequest() {
        page = 1;
        amount = 10;
    }

    // 팩토리 메소드를 사용해서 PageRequest 생성자 사용
    public static PageRequest of(int page, int amount) {
        return new PageRequest(page, amount);
    }


    public int getOffset() { // offset 값 계산
        return (page - 1) * amount;
    }
}