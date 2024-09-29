package com.be.cart.controller;

import com.be.cart.domain.CartItemVO;
import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userNum}")
    public void getCartItems(@PathVariable int userNum, HttpServletRequest request) {
            HttpSession session = request.getSession();
            List<CartItemVO> cartList = cartService.getCartList(userNum).stream().map(CartItemResDto::toVO).toList();
            session.setAttribute("cartList", cartList);

//          System.out.println(session.getAttribute("cartList"));

//          return ResponseEntity.ok(cartService.getCartList(userNum));
    }

    @PostMapping
    public ResponseEntity<CartItemResDto> addCartItem(CartItemReqDto cartItem, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("userNum") == null) {
            response.sendRedirect("/api/login");
        }
        cartItem.setUserNum((Integer) session.getAttribute("userNum"));

        List<CartItemVO> cartList = (List<CartItemVO>) session.getAttribute("cartList");
        cartList.add(cartItem.toVO());

        session.setAttribute("cartList", cartList);
        return ResponseEntity.ok(cartService.addCartItem(cartItem));
    }

    @DeleteMapping("/{cartID}")
    public void deleteCartItem(@PathVariable int cartID, HttpServletRequest request) {
        List<CartItemVO> cartList = (List<CartItemVO>) request.getSession().getAttribute("cartList");
        if(cartList != null) {
            return;
        }

        for(int i = 0; i < cartList.size(); i++) {
            if(cartList.get(i).getCartId() == cartID) cartList.remove(i);
        }
        request.getSession().setAttribute("cartList", cartList);

        cartService.deleteCartItem(cartID);
    }
}

