package com.sparta.week03.dto;

import com.sparta.week03.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String author;
    private String password;

    public Post toEntity(){
        return new Post(title, author, content, password);
    }

}
