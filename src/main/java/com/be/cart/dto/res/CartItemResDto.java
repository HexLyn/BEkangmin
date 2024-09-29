package com.be.cart.dto.res;

import com.be.cart.domain.CartItemVO;
import com.be.portfolio.domain.PortfolioItemVO;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResDto {
    private int cartId;
    private int productId;
    private int userNum;
    private String productType;
    private String provider;
    private String productName;
    private Double expectedReturn;
    private Double interestRate;

    public static CartItemResDto of(CartItemVO cartVO) {
        return cartVO == null ? null : CartItemResDto.builder()
                .cartId(cartVO.getCartId())
                .productId(cartVO.getProductId())
                .userNum(cartVO.getUserNum())
                .productType(cartVO.getProductType())
                .provider(cartVO.getProvider())
                .productName(cartVO.getProductName())
                .expectedReturn(cartVO.getExpectedReturn())
                .interestRate(cartVO.getInterestRate())
                .build();
    }

    public static CartItemVO toVO(CartItemResDto cartItemResDto) {
        return CartItemVO.builder()
                .cartId(cartItemResDto.getCartId())
                .productId(cartItemResDto.getProductId())
                .userNum(cartItemResDto.getUserNum())
                .productType(cartItemResDto.getProductType())
                .provider(cartItemResDto.getProvider())
                .productName(cartItemResDto.getProductName())
                .expectedReturn(cartItemResDto.getExpectedReturn())
                .interestRate(cartItemResDto.getInterestRate())
                .build();
    }
}
