package com.be.member.dto.res;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberRegisterResDto {

    private String memberID;
    private String email;
    private String memberName;
    private String password;
    private String birth;
    private String gender;
    private String regDate;
}
