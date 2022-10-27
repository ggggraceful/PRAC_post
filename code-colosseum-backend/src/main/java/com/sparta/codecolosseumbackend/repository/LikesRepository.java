package com.sparta.codecolosseumbackend.repository;

import com.sparta.codecolosseumbackend.entity.Likes;
import com.sparta.codecolosseumbackend.entity.Member;
import com.sparta.codecolosseumbackend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByProblem(Problem problem);
    Optional<Likes> findAllByProblemAndMember(Problem problem, Member member);
}
