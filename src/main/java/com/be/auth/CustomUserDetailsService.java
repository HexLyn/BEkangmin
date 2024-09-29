package com.be.auth;

import com.be.exception.CustomException;
import com.be.member.domain.Member;
import com.be.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.be.common.code.ErrorCode.MEMBER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    // ID로 조회
    @Override
    public UserDetails loadUserByUsername(String memberID) throws UsernameNotFoundException {
        Member member = memberMapper.findOneByMemberID(memberID);
        if (member == null) {
            throw new CustomException(MEMBER_NOT_FOUND);
        }

        return new org.springframework.security.core.userdetails.User(member.getMemberName(),
                member.getPassword(),
                member.getAuthorities());
    }

    // 고유 넘버로 조회
    public Member loadUserByMemberNum(Long memberNum) {
        Member member = memberMapper.findOneByMemberNum(memberNum);
        if (member == null) {
            throw new CustomException(MEMBER_NOT_FOUND);
        }

        return member;
    }

//    public User loadAdminByUserId(Long userId) {
//        return userRepository.findByIdAndIsDeletedIsNull(userId)
//                .orElseThrow(() -> {
//                    throw new CustomException(TOKEN_UNAUTHORIZED);
//                });
//    }


}
