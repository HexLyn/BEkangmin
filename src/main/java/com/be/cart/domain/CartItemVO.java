package com.be.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemVO {
    private int cartId;
    private int productId;
    private long memberNum;
    private String productType;
    private String provider;
    private String productName;
    private Double expectedReturn;
    private Double interestRate;
}
