package com.be.member.domain;

import com.be.member.domain.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberRole {

    private Long id;

    private Long memberID;

    private String role;


    @Builder
    public MemberRole(Long memberID, Role role) {
        this.memberID = memberID;
        this.role = role.name();
    }
}
