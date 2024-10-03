package com.be.member.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class UpdateMemberPasswordReqDto {

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 8~30자만 가능합니다.")
    @Pattern(regexp = "^(?=.*[A-z])(?=.*\\d)(?=.*[#$@!%&*?])[A-z\\d#$@!%&*?]+$",
            message = "비밀번호는 영어, 숫자, 특수문자(#$@!%&*?)의 조합으로 입력해 주세요.")
    private String password;

    @NotBlank(message = "새 비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 8~30자만 가능합니다.")
    @Pattern(regexp = "^(?=.*[A-z])(?=.*\\d)(?=.*[#$@!%&*?])[A-z\\d#$@!%&*?]+$",
            message = "비밀번호는 영어, 숫자, 특수문자(#$@!%&*?)의 조합으로 입력해 주세요.")
    private String newPassword;
}
