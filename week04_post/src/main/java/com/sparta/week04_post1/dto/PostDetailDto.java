package  com.sparta.week04_post1.dto;

//상세페이지 확인 Dto


import com.sparta.week04_post1.entity.Post;
import java.time.LocalDateTime;

public class PostDetailDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long id;
    private String title;
    private String author;
    private String content;


    public PostDetailDto( com.sparta.week04_post1.entity.Post post){

        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
        id = post.getId();
        title = post.getTitle();
        author = post.getAuthor();
        content = post.getContent();

    }

}