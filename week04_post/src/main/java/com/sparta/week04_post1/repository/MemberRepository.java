package com.sparta.week04_post1.repository;

import com.sparta.week04_post1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    //중복 가입 방지를 위한 existsByEmail
    boolean existsByEmail(String email);
}