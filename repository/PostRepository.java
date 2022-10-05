package com.sparta.week03.repository;


import com.sparta.week03.domain.Post;
import com.sparta.week03.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

        
public interface PostRepository extends JpaRepository<Post, Long> { 

    List<Post> findAllByOrderByModifiedAtDesc();

 }
