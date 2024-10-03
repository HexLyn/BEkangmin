package com.be.cart.dto.req;

import com.be.cart.domain.CartItemVO;
import com.be.cart.dto.res.CartItemResDto;
import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemReqDto {
    private int cartId;
    private int productId;
    private long memberNum;
    private String productType;
    private String provider;
    private String productName;
    private Double expectedReturn;
    private Double interestRate;

    public CartItemVO toVO() {
        return CartItemVO.builder()
                .productId(productId)
                .memberNum(memberNum)
                .productType(productType)
                .provider(provider)
                .productName(productName)
                .expectedReturn(expectedReturn)
                .interestRate(interestRate)
                .build();
    }
}
