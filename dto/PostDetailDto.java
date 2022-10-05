package com.sparta.week03.dto;

import com.sparta.week03.domain.Post;
import java.time.LocalDateTime;

public class PostDetailDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long id;
    private String title;
    private String author;
    private String content;


    public PostDetailDto(Post post){

        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
        id = post.getId();
        title = post.getTitle();
        author = post.getAuthor();
        content = post.getContent();

    }

}
