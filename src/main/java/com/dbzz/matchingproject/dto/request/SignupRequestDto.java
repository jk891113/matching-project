package com.dbzz.matchingproject.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @Size(min = 4, max = 15, message = "아이디는 4글자 이상, 15글자 이하로 작성하세요.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "아이디 형식에 맞춰 작성하세요. 영문(소문자), 숫자만 작성 가능합니다.")
    private String userId;

    @Size(min = 8, max = 15, message = "비밀번호는 8글자 이상, 15글자 이하로 작성하세요.")
    @Pattern(regexp = "^[0-9a-zA-Z!@#$%^&*()_=+?]*$", message = "영문(대, 소문자), 숫자, 특수문자를 혼합하세요.")
    private String password;

    private String adminToken;
}
