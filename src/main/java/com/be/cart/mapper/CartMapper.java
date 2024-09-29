package com.be.cart.mapper;

import com.be.cart.domain.CartItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<CartItemVO> getCartItemList(int userNum);
    CartItemVO checkCartItem(@Param("userNum") int userNum, @Param("productId") int productId);
    int addCartItem(CartItemVO cartVO);
    void deleteCartItem(int cartId);
}
