package com.be.finance.mapper;

import com.be.finance.domain.FundProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FundProductMapper {

    void insertFundProduct(FundProductVO fundProductVO);

    // 펀드 리스트 조회
    List<FundProductVO> getFundProductsList();

    // 펀드 검색 리스트 조회
    List<FundProductVO> searchFundProducts(@Param("keyword") String keyword);

    // 특정 채권 상품 상세 정보 조회
    FundProductVO getFundProductDetail(int productId);
}
