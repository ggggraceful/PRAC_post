package com.sparta.week03.contorller;

//큰 기능

import com.sparta.week03.dto.*;
import com.sparta.week03.repository.PostRepository;
import com.sparta.week03.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor //필요한 필드가 있을 때 그걸로 생성자를 만들어 줌
@RestController //json 타입 데이터로
public class PostController {

    private final PostService postService;




//*****게시글 목록 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getAll(){
        return postService.getAll();
    }


//*****게시글 상세페이지 조회
    @GetMapping("/api/posts/{id}")
    public PostDetailDto getOne (@PathVariable ("id")Long id){
        return postService.getOne(id);
    }


//*****게시물 작성
//                         post에 s를 붙여 전체데이터를 가져오는 주소를 그대로 사용
//  @PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.
//    @PostMapping("/api/posts")
//    public Post createPost(@RequestBody PostRequestDto requestDto) {
//        //RequestBody : 추가.변경될 값이 들어있는 바디
//        //requestDto: 생성 요청, 필요한 정보를 가져옴
//
//        // 저장하는 것은 Dto가 아니라 Post이니, Dto의 정보를 post에 담아야 합니다.
//        // 잠시 뒤 새로운 생성자를 만듭니다.
//        Post post = requestDto.toEntity();
//
//        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
//        return postRepository.save(post);
//    }


//******게시글 작성
//      Http body 에 데이터가 담겨있다.
//      title - asd
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
                                    //@RequestBody : 추가.변경될 값이 들어있는 바디
        return postService.create(requestDto);
    }

//******비밀번호 확인
//    Http body 에 데이터가 담겨있다.
    @PostMapping("/posts/{id}")
    public PasswordCheckResponseDto passwordCheck(@PathVariable ("id") Long id, @RequestBody PasswordCheckRequestDto passwordCheckRequestDto){
        return postService.passwordCheck(id, passwordCheckRequestDto);
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