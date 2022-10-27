package com.sparta.codecolosseumbackend.repository;


import com.sparta.codecolosseumbackend.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
