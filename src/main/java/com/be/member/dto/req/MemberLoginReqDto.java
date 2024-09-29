package com.be.member.dto.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginReqDto {

    private String memberID;
    private String password;

}
