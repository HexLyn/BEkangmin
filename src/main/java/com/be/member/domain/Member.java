package com.be.member.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

    private long memberNum;
    private String memberID;
    private String memberName;
    private String email;
    private String password;
    private String birth;
    private String gender;
    private int preference;
    private int investScore;
    private String regDate;

    private List<MemberRole> memberRoles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.memberRoles.stream()
                .map(memberRole ->
                        new SimpleGrantedAuthority(memberRole.getRole()))
                .collect(Collectors.toList());
    }

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for(MemberRole memberRole : this.memberRoles)
            roles.add(memberRole.getRole());

        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.memberName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Builder(builderMethodName = "registerMemberBuilder" , builderClassName = "registerMemberBuilder")
    public Member(String memberID, String memberName, String email, String password,
                  String birth, String gender, String regDate) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.regDate = regDate;
    }
}
