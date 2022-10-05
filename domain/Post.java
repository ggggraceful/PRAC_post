package com.sparta.week03.domain;

import com.sparta.week03.dto.PostRequestDto;
import com.sparta.week03.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor 
@Getter 
@Entity 
public class Post extends Timestamped { 

    @GeneratedValue(strategy = GenerationType.AUTO)
                             
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;


    public Post(String title, String content, String author, String password) {
        this.title    = title;
        this.content  = content;
        this.author   = author;
        this.password = password;

    }

    
    public void update(String title, String content, String author, String password) {
        this.title    = title;
        this.content  = content;
        this.author   = author;
        this.password = password;

    }

}
