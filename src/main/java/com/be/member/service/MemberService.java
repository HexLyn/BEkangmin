package com.be.member.service;

import com.be.exception.CustomException;
import com.be.member.domain.Member;
import com.be.member.domain.type.Role;
import com.be.member.dto.req.MemberLoginReqDto;
import com.be.member.dto.req.MemberRegisterReqDto;
import com.be.member.domain.MemberRole;
import com.be.member.mapper.MemberMapper;
import com.be.member.mapper.MemberRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.be.common.code.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberMapper memberMapper;
    private final MemberRoleMapper memberRoleMapper;

    @Bean
    private PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}

    public Member registerMember(MemberRegisterReqDto reqDto) {

        validateMemberID(reqDto.getMemberID());
        validateMemberEmail(reqDto.getEmail());
        checkPasswordMatching(reqDto.getPassword(), reqDto.getReEnteredPassword());
        String encodedPassword = encodePassword(reqDto.getPassword());
        Member member = reqDto.toMember(encodedPassword);

        saveMember(member);
        member = fineOneMemberById(member.getMemberID());

        MemberRole memberRoleDto = createMemberRoleDto(member);
        log.info(memberRoleDto.toString());
        saveMemberRole(memberRoleDto);
        member.addMemberRole(getMemberRolesByMemberNum(member.getMemberNum()));

        return member;
    }


    public Member login(MemberLoginReqDto memberLoginReqDto) {
        Member member = fineOneMemberById(memberLoginReqDto.getMemberID());

        boolean isVerified = verifyPassword(member, memberLoginReqDto.getPassword());
        if (!isVerified) {
            throw new CustomException(LOGIN_UNAUTHENTICATED);
        }
        member.addMemberRole(getMemberRolesByMemberNum(member.getMemberNum()));

        return member;
    }

    private Member fineOneMemberById(String memberId) {
        Optional<Member> member = Optional.ofNullable(memberMapper.findOneByMemberID(memberId));

        if (member.isEmpty()) {
            log.info("사용자가 존재하지 않습니다.");
            throw new CustomException(LOGIN_UNAUTHENTICATED);
        }

        return member.get();
    }

    private boolean verifyPassword(Member member, String requestPassword) {
        // 로그인 시 비밀번호 일치여부 확인
        return passwordEncoder().matches(requestPassword, member.getPassword());

    }

    public void validateMemberID(String memberID) {

        isExistID(memberID);
    }

    public void validateMemberEmail(String Email) {

        isExistMemberEmail(Email);
    }

    public void checkPasswordMatching(String password, String reEnteredPassword) {
        // 회원가입 시 비밀번호 일치 여부 확인
        if (!password.equals(reEnteredPassword))
            throw new CustomException(PASSWORD_MATCH_INVALID);
    }


    public String encodePassword(String password) {
        return passwordEncoder().encode(password);
    }

    public void isExistID(String memberID) {
        Optional<Member> member = Optional.ofNullable(memberMapper.findOneByMemberID(memberID));

        if (member.isPresent()) {
            log.info("ID already exists: {}", memberID);
            throw new CustomException(EXISTING_MEMBER_ID);
        }
    }

    public void isExistMemberEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberMapper.findOneByMemberEmail(email));

        if (member.isPresent()) {
            throw new CustomException(EXISTING_EMAIL);
        }
    }

    /**
     * 회원 권한 생성
     */
    private MemberRole createMemberRoleDto(Member member) {
        return MemberRole.builder()
                .memberID(member.getMemberNum())
                .role(Role.MEMBER)
                .build();
    }

    private void saveMember(Member member) {
        try {
            memberMapper.insert(member);
        } catch (RuntimeException e) {
            throw new CustomException(e, SERVER_ERROR);
        }
    }

    private void saveMemberRole(MemberRole memberRoleDto) {
        try {
            memberRoleMapper.save(memberRoleDto);
        } catch (RuntimeException e) {
            throw new CustomException(e, SERVER_ERROR);
        }
    }

    private List<MemberRole> getMemberRolesByMemberNum(long memberNum) {
        try {
            return memberRoleMapper.findMemberRoleByMemberNum(memberNum);
        } catch (RuntimeException e) {
            throw new CustomException(e, SERVER_ERROR);
        }
    }

    public void updatePassword(Member member, String password, String newPassword) {
        boolean isVerified = verifyPassword(member, password);
        if (!isVerified) {
            throw new CustomException(PASSWORD_INVALID);
        }

        member.updatePassword(encodePassword(newPassword));

        try {
            memberMapper.updatePassword(member);
        } catch (RuntimeException e) {
            throw new CustomException(e, SERVER_ERROR);
        }

    }

}
