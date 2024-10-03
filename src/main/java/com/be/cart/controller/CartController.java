package com.be.cart.controller;

import com.be.auth.JwtProvider;
import com.be.cart.dto.req.CartItemReqDto;
import com.be.cart.dto.res.CartItemResDto;
import com.be.cart.service.CartService;
import com.be.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Log4j
public class CartController {
    private final CartService cartService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public List<CartItemResDto> getCartItems(HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();

            // 테스트용 코드
//            List<CartItemResDto> testList = cartService.getCartList(1L);
//            session.setAttribute("cartList", testList);
            //

            List<CartItemResDto> cartList = (List<CartItemResDto>) session.getAttribute("cartList");
            log.info(cartList);
            return cartList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니 호출 과정에서 에러 발생");
        }
        return null;
    }

    @PostMapping
    public void addCartItem(@RequestBody @Valid CartItemReqDto cartItem, HttpServletRequest request) {
        try {
            // 토큰에서 memberNum 가져오기
            String token = request.getHeader("Refresh-Token") == null ?
                    request.getHeader(AUTHORIZATION) : request.getHeader("Refresh-Token");
            Member member = jwtProvider.authorizeUserAccessJwt(token);
            long memberNum = member.getMemberNum(); // 사용자 번호 추출

            cartItem.setMemberNum(memberNum);

            HttpSession session = request.getSession();
            List<CartItemResDto> temp = (List<CartItemResDto>) session.getAttribute("cartList");
            List<CartItemResDto> cartList = new ArrayList<>(temp);

            for(CartItemResDto cartItemResDto : cartList) {
                if(cartItemResDto.getProductId() == cartItem.getProductId()) throw new Exception("중복 아이템 입니다");
            }

            cartItem.setCartId(cartService.addCartItem(cartItem).getCartId());
            cartList.add(CartItemResDto.of(cartItem));

            session.setAttribute("cartList", cartList);
            log.info(session.getAttribute("cartList"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니 담기에서 에러 발생");
        }
    }

    @DeleteMapping("/{cartID}")
    public void deleteCartItem(@PathVariable int cartID, HttpServletRequest request) {
        try {
            List<CartItemResDto> temp = (List<CartItemResDto>) request.getSession().getAttribute("cartList");
            List<CartItemResDto> cartList = new ArrayList<>(temp);

            for(int i = 0; i < cartList.size(); i++) {
                if(cartList.get(i).getCartId() == cartID) cartList.remove(i);
            }
            request.getSession().setAttribute("cartList", cartList);
            log.info(request.getSession().getAttribute("cartList"));

            cartService.deleteCartItem(cartID);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니 삭제 과정에서 에러 발생");
        }
    }
}

