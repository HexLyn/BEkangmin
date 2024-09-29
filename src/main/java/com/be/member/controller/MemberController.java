package com.be.member.controller;


import com.be.auth.JwtProvider;
import com.be.common.dto.DefaultResDto;
import com.be.member.domain.Member;
import com.be.member.dto.req.MemberLoginReqDto;
import com.be.member.dto.req.MemberRegisterReqDto;
import com.be.member.dto.res.MemberDefaultResDto;
import com.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.be.common.code.SuccessCode.MEMBER_LOGIN;
import static com.be.common.code.SuccessCode.USER_REGISTERED;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @GetMapping("")
    public String tesss() {
        log.info("aget");
        return "tess";
    }


    @PostMapping("/register")
    public ResponseEntity<DefaultResDto<Object>> register(HttpServletRequest servletRequest, @RequestBody @Valid MemberRegisterReqDto reqDto) {
        //jwtProvider.authorizeGuestAccessJwt(servletRequest.getHeader(AUTHORIZATION));

        log.info("register = " + reqDto.toString());
        Member member = memberService.registerMember(reqDto);

        HttpHeaders headers = jwtProvider.generateUserJwt(member.getMemberNum(), member.getRoles());
        MemberDefaultResDto response = new MemberDefaultResDto(member);

        return ResponseEntity.status(USER_REGISTERED.getHttpStatus())
                .headers(headers)
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(USER_REGISTERED.name())
                        .responseMessage(USER_REGISTERED.getMessage())
                        .data(response)
                        .build());
    }



    @PostMapping("/login")
    public ResponseEntity<DefaultResDto<Object>> login(@RequestBody @Valid MemberLoginReqDto memberLoginReqDto) {
        Member member = memberService.login(memberLoginReqDto);

        HttpHeaders headers = jwtProvider.generateUserJwt(member.getMemberNum(), member.getRoles());
        MemberDefaultResDto response = new MemberDefaultResDto(member);


        log.info(response.toString());
        return ResponseEntity.status(MEMBER_LOGIN.getHttpStatus())
                .headers(headers)
                .body(DefaultResDto.singleDataBuilder()
                        .responseCode(MEMBER_LOGIN.name())
                        .responseMessage(MEMBER_LOGIN.getMessage())
                        .data(response)
                        .build());
    }
}
