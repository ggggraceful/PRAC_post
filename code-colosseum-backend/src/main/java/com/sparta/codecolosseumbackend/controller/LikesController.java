package com.sparta.codecolosseumbackend.controller;

import com.sparta.codecolosseumbackend.dto.response.ResponseDto;
import com.sparta.codecolosseumbackend.entity.Member;
import com.sparta.codecolosseumbackend.security.UserDetailImp;
import com.sparta.codecolosseumbackend.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikesController {
    private final LikesService likesService;

    // 좋아요 기능
    @PostMapping("/api/auth/problem/{problemId}/likes")
    public ResponseDto<?> like(@AuthenticationPrincipal UserDetailImp userDetails, @PathVariable Long problemId) {
        return likesService.like2(userDetails.getMember(), problemId);
    }
}
