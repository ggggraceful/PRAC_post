package com.sparta.week03.dto;

import com.sparta.week03.domain.Post;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
public class PostResponseDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long id;
    private String title;
    private String content;
    private String author;


    public PostResponseDto(Post post) {

        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
        id = post.getId();
        title = post.getTitle();
        author = post.getAuthor();
        content = post.getContent();
    }
}
