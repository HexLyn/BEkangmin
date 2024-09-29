package com.be.finance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private int productId;
    private char productType;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public char getProductType() {
        return productType;
    }

    public void setProductType(char productType) {
        this.productType = productType;
    }
}
