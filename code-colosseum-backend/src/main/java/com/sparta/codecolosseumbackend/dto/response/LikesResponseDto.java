package com.sparta.codecolosseumbackend.dto.response;

import com.sparta.codecolosseumbackend.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LikesResponseDto {
    private int likeNum;

    private boolean likeCheck;

    public LikesResponseDto(Problem problem, boolean likeCheck) {
        this.likeNum = problem.getLikeNum();
        this.likeCheck = likeCheck;
    }
}
