package com.be.cart.service;

import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;

import java.util.List;

public interface CartService {
    List<CartItemResDto> getCartList(long memberNum);
    List<CartItemResDto> getSavingsInCart (long memberNum);
    List<CartItemResDto> getFundInCart (long memberNum);
    List<CartItemResDto> getBondInCart (long memberNum);
    CartItemResDto addCartItem(CartItemReqDto cart);
    void deleteCartItem(int cartId);
    boolean checkCartItem(CartItemReqDto cart);
}
