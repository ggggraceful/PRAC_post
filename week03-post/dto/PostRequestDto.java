package com.sparta.week03.dto;

//
//에러해결

import com.sparta.week03.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

//Post.java에 뜨는 에러를 해결하려면 PostRequestDto.java를 만들어야 한다.
@Getter
@AllArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String author;
    private String password;

    //dto를 entity로 바꿔준다.
    public Post toEntity() {
        return new Post(title, author, content, password);
    }

}