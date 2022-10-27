package com.sparta.codecolosseumbackend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    @NotBlank
    @Pattern(regexp = "[a-z0-9]{4,12}", message = "아이디양식을 확인해주세요!")
    private String username;

    @NotBlank
    @Pattern(regexp = "[a-z0-9]{4,32}", message = "비밀번호양식을 확인해주세요!")
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank
    private String nickname;

    @NotBlank
    private String tier;
}
