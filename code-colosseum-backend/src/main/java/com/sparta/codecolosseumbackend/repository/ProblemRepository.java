package com.sparta.codecolosseumbackend.repository;

import com.sparta.codecolosseumbackend.dto.response.ProblemResponseDto;
import com.sparta.codecolosseumbackend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findAllByOrderByCreatedAtDesc();
    List<Problem> findAllByOrderByLikeNumDesc();
}
