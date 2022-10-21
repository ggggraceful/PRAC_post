package com.sparta.post03.controller;

import com.sparta.post03.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/api/auth/post/{id}/like")
	public ResponseEntity<?> pushPostLike(@PathVariable Long id, HttpServletRequest request){
		return likeService.pushPostLike(id, request);
	}

}