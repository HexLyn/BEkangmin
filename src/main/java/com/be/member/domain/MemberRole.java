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

    private int memberId;

    private Member member;

    private String role;

    @Builder
    public MemberRole(Member member, Role role) {
        this.member = member;
        this.role = role.name();
    }
}
