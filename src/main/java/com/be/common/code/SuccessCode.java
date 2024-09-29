package com.be.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum SuccessCode {


    /**
     * User Controller
     */
    // 200 OK
    USERNAME_AVAILABLE(OK, "아이디 중복 확인을 했습니다."),
    EMAIL_AVAILABLE(OK, "이메일 중복 확인을 했습니다."),

    //201
    USER_REGISTERED(CREATED, "회원 가입을 했습니다."),


    MEMBER_LOGIN(OK, "회원이 로그인했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
