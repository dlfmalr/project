package com.korea.project.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String loginId;
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password;
    @NotEmpty(message = "이메일은 필수 항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
}
