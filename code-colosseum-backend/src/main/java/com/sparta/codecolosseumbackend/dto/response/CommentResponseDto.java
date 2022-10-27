package com.sparta.codecolosseumbackend.dto.response;

import com.sparta.codecolosseumbackend.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

	private Long commentId;
	private String comment;
	private String nickname;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private boolean isLoading;

	public CommentResponseDto(Comment comment) {
		this.commentId = comment.getId();
		this.nickname = comment.getMember().getNickname();
		this.comment = comment.getContent();
		this.createdAt = comment.getCreatedAt();
		this.modifiedAt = comment.getModifiedAt();
		this.isLoading = comment.isLoading();
	}

}