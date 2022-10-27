package com.sparta.codecolosseumbackend.entity;

import com.sparta.codecolosseumbackend.dto.request.ProblemRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private int likeNum;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    public Problem(Member member, ProblemRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.member = member;
    }

    // 업데이트
    public void updateProblem(ProblemRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
    }

    // 좋아요 수 증가/감소
    public void likeNumChange(int num){
        if (num == 0) {
            this.likeNum ++;
        } else {
            this.likeNum --;
        }
    }
}
