package com.sparta.week04_post1.controller;

//사용자 인증 과정
//회원가입 / 로그인 / 재발급 을 처리하는 API 입니다.
//SecurityConfig 에서 /auth/** 요청은 전부 허용했기 때문에 토큰 검증 로직을 타지 않습니다.
//MemberRequestDto 에는 사용자가 로그인 시도한 ID / PW String 이 존재합니다.
//TokenRequestDto 에는 재발급을 위한 AccessToken / RefreshToken String 이 존재합니다.

import com.sparta.week04_post1.dto.MemberRequestDto;
import com.sparta.week04_post1.dto.MemberResponseDto;
import com.sparta.week04_post1.dto.TokenDto;
import com.sparta.week04_post1.dto.TokenRequestDto;
import com.sparta.week04_post1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

//
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

//재발급
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}