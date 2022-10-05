package com.sparta.week03.contorller;

import com.sparta.week03.domain.Post;
import com.sparta.week03.dto.PostDetailDto;
import com.sparta.week03.dto.PostRequestDto;
import com.sparta.week03.dto.PostResponseDto;
import com.sparta.week03.repository.PostRepository;
import com.sparta.week03.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor 
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;


    @GetMapping("/api/posts")
    public List<PostResponseDto> getAll(){
        return postService.getAll();
    }


    @GetMapping("/api/posts/{id}")
    public PostDetailDto getOne (@PathVariable ("id")Long id){
        return postService.getOne(id);
    }


    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = requestDto.toEntity();
        return postRepository.save(post);
    }


    @PutMapping("/api/posts/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }


    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return id;
    }



}
