package com.be.finance.mapper;


import com.be.finance.domain.SavingProductRatesVO;
import com.be.finance.domain.SavingProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SavingProductMapper {

    // 예/적금 상품 저장
    void insertSavingProduct(SavingProductVO product);

    // 기간별 수익률 저장
    void insertSavingProductRate(SavingProductRatesVO rate);

    // 예금 데이터 리스트 조회
    List<SavingProductVO> getDepositProducts();

    // 예금 기간별 수익률 리스트 조회
    List<SavingProductRatesVO> getDepositRates();

    // 적금 데이터 리스트 조회
    List<SavingProductVO> getSavingProducts();

    // 적금 기간별 수익률 리스트 조회
    List<SavingProductRatesVO> getSavingRates(List<SavingProductVO> productList);

    // 예금 상품 검색 리스트 조회
    List<SavingProductVO> searchDepositProducts(@Param("keyword") String keyword);

    // 검색된 예금 상품 기간별 수익률 조회
    List<SavingProductRatesVO> searchDepositRatesProducts(List<SavingProductVO> productList);

    // 적금 상품 검색 리스트 조회
    List<SavingProductVO> searchSavingProducts(@Param("keyword") String keyword);

    // 검색된 적금 상품 기간별 수익률 조회
    List<SavingProductRatesVO> searchSavingRatesProducts(List<SavingProductVO> productList);

    // 페이징된 예금 상품에 대한 기간별 수익률 조회
    List<SavingProductRatesVO> getDepositRatesByProductIds(@Param("productIds") List<Integer> productIds);


    // 페이징된 적금 상품에 대한 기간별 수익률 조회
    List<SavingProductRatesVO> getSavingRatesByProductIds(@Param("productIds") List<Integer> productIds);

    List<SavingProductRatesVO> searchDepositRatesByProductIds(@Param("productIds") List<Integer> productIds);

    List<SavingProductRatesVO> searchSavingRatesByProductIds(@Param("productIds") List<Integer> productIds);
}
