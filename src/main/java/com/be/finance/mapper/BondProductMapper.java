package com.be.finance.mapper;

import com.be.finance.domain.BondProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BondProductMapper {
    void insertBondProduct(BondProductVO bondProductVO);

    // 전체 채권 리스트 조회
    List<BondProductVO> getBondProductsList();

    // 채권 검색 리스트 조회
    List<BondProductVO> searchBondProducts(@Param("keyword") String keyword);

    // 특정 채권 상품 상세 정보 조회
    BondProductVO getBondProductDetail(int productId);
}
