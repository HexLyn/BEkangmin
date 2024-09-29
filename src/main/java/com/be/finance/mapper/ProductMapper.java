package com.be.finance.mapper;

import com.be.finance.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    // 금융 상품(Product 테이블) 저장
    void insertProduct(ProductVO product);

}
