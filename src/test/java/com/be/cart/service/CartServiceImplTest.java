package com.be.cart.service;

import com.be.cart.domain.CartItemVO;
import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.mapper.CartMapper;
import com.be.config.RootConfig;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RootConfig.class,
})
@Log4j
class CartServiceImplTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    void getCartList() {
        System.out.println(cartMapper.getCartItemList(1)
                .stream().map(CartItemResDto::of).toList());

    }

    @Test
    void addCartItem() {
        CartItemReqDto cart = new CartItemReqDto();

        cart.setProductId(13);
        cart.setUserNum(1);
        cart.setProductType("S");
        cart.setProvider("삼성");
        cart.setProductName("1펀드");
        cart.setExpectedReturn(15.2);

        CartItemVO cartVO = cart.toVO();
        CartItemResDto cartResDto = CartItemResDto.of(cartVO);
        cartResDto.setCartId(cartMapper.addCartItem(cartVO));

        System.out.println(cartResDto);
    }

    @Test
    void deleteCartItem() {
        cartMapper.deleteCartItem(2);
    }

    @Test
    void checkCartItem() {
        System.out.println(cartMapper.checkCartItem(1, 1) == null ? "false" : "true");
    }
}