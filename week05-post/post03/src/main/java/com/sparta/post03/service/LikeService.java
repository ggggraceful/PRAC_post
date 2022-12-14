package com.sparta.post03.service;

import com.sparta.post03.dto.response.ResponseDto;
import com.sparta.post03.entity.Member;
import com.sparta.post03.entity.Post;
import com.sparta.post03.entity.PostLike;
import com.sparta.post03.exception.entityException.likeException.LikeIsAlreadyExistException;
import com.sparta.post03.exception.entityException.postException.AccessTokenNotExistException;
import com.sparta.post03.exception.entityException.postException.PostIdNotFoundException;
import com.sparta.post03.exception.entityException.postException.RefreshTokenNotExistException;
import com.sparta.post03.exception.entityException.postException.TokenInvalidException;
import com.sparta.post03.jwt.provider.JwtProvider;
import com.sparta.post03.repository.LikeRepository;
import com.sparta.post03.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikeRepository likeRepository;
	private final JwtProvider jwtProvider;
	private final PostRepository postRepository;

	@Transactional
	public Member validateMember(HttpServletRequest request) {
		if (!jwtProvider.validateToken(request.getHeader("Refresh-Token"))) {
			return null;
		}
		return jwtProvider.getMemberFromAuthentication();
	}

	@Transactional(readOnly = true)
	public Post isPresentPost(Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		return optionalPost.orElse(null);
	}

	@Transactional
	public ResponseEntity<?> pushPostLike(Long postId, HttpServletRequest request) {
		if(null == request.getHeader("Authorization")){
			throw new RefreshTokenNotExistException();
		}
		if(null == request.getHeader("Refresh-Token")){
			throw new AccessTokenNotExistException();
		}
		Member member = validateMember(request);

		if(null == member){
			throw new TokenInvalidException();
		}
		Post post = isPresentPost(postId);
		if(post == null ){
			throw new PostIdNotFoundException();
		}
		Optional<PostLike> findPostLike = likeRepository.findByPostAndMember(post, member);
		findPostLike.ifPresentOrElse(
// ????????? ???????????? ??????
				postLike -> {
					likeRepository.delete(postLike);
					post.discountLike(postLike);
					throw new LikeIsAlreadyExistException();
				},
//????????? ?????? ?????? ????????? ??????
				() -> {
					PostLike postLike = PostLike.builder()
							.post(post)
							.member(member)
							.build();
					likeRepository.save(postLike);
				});
		return ResponseEntity.ok(ResponseDto.success(true));
	}
}