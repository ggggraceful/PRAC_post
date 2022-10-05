package com.sparta.week03.contorller;

//큰 기능

import com.sparta.week03.domain.Post;
import com.sparta.week03.dto.PostDetailDto;
import com.sparta.week03.dto.PostRequestDto;
import com.sparta.week03.dto.PostResponseDto;
import com.sparta.week03.repository.PostRepository;
import com.sparta.week03.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor //필요한 필드가 있을 때 그걸로 생성자를 만들어 줌
@RestController //json타입 데이터로
public class PostController {

    private final PostRepository postRepository;
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
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        //RequestBody : 추가.변경될 값이 들어있는 바디
        //requestDto: 생성 요청, 필요한 정보를 가져옴

        // 저장하는 것은 Dto가 아니라 Post이니, Dto의 정보를 post에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        Post post = requestDto.toEntity();

        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return postRepository.save(post);
    }


//*****게시물 수정
    @PutMapping("/api/posts/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }




//*****게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable("id") Long id) {
                           //@PathVariable: 주소에 변수를 넘겨줌, id 값을 주소의 {id}에 대입시켜줌
        postRepository.deleteById(id);
        return id;
    }



}