package com.sparta.week04_post1.controller;

//큰 기능

import com.sparta.week04_post1.dto.*;
import com.sparta.week04_post1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor //필요한 필드가 있을 때 그걸로 생성자를 만들어 줌
@RestController //json 타입 데이터로
public class PostController {

    private final PostService postService;




//*****게시글 목록 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getAll(){
        return postService.getAll();
    }


//*****게시글 상세페이지 조회
    @GetMapping("/posts/{id}")
    public PostDetailDto getOne (@PathVariable ("id")Long id){
        return postService.getOne(id);
    }


//******게시글 작성
//      Http body 에 데이터가 담겨있다.
//      title - asd
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
                                    //@RequestBody : 추가.변경될 값이 들어있는 바디
        return postService.create(requestDto);
    }



//*****게시글 수정
//    게시글 id, 바꿀 데이터가 필요
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePosts(@PathVariable("id") Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.update(id,postRequestDto);
    }


//*****게시글 삭제
    @DeleteMapping("/posts/{id}")
    public PostDeleteResponseDto deletePost(@PathVariable("id") Long id) {
                           //@PathVariable: 주소에 변수를 넘겨줌, id 값을 주소의 {id}에 대입시켜줌
        return postService.delete(id);
    }



}