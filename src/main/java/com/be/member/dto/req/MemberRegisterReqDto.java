package com.be.member.dto.req;

import com.be.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class MemberRegisterReqDto {
    {
        log.info("MemberRegisterReqDto constructor");
    }


    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Size(min = 5, max = 15, message = "아이디는 5~15자만 가능합니다.")
    @Pattern(regexp = "^(?=.*[a-z0-9])[a-z0-9]+$", message = "아이디는 소문자 영어와 숫자의 조합으로 입력해 주세요.")
    private String memberID;

    private String email;

    private String memberName;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 8~30자만 가능합니다.")
    @Pattern(regexp = "^(?=.*[A-z])(?=.*\\d)(?=.*[#$@!%&*?])[A-z\\d#$@!%&*?]+$",
            message = "비밀번호는 영어, 숫자, 특수문자(#$@!%&*?)의 조합으로 입력해 주세요.")
    private String password;

    @NotBlank(message = "비밀번호 재입력은 필수 입력입니다.")
    private String reEnteredPassword;

    private String birth;

    @Pattern(regexp = "^(남|여)", message = "성별을 선택해주세요.")
    private String gender;

    public Member toMember(String encodedPassword) {

        return Member.registerMemberBuilder()
                .memberID(this.memberID)
                .email(this.getEmail())
                .memberName(this.getMemberName())
                .password(encodedPassword)
                .birth(this.getBirth())
                .gender(this.getGender())
                .regDate(LocalDate.now().toString()).build();

    }
}
