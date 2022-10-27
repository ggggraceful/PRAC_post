package com.sparta.codecolosseumbackend.entity;

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
public class Likes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public Likes(Problem problem, Member member) {
        this.problem = problem;
        this.member = member;
    }
}
