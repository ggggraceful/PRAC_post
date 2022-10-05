package com.sparta.week03.service;

//변경.수정이 잘되었는지 확인

import com.sparta.week03.domain.Post;
import com.sparta.week03.dto.PostDetailDto;
import com.sparta.week03.dto.PostRequestDto;
import com.sparta.week03.dto.PostResponseDto;
import com.sparta.week03.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // 생성자 주입: fanal 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어줌
@Service
public class PostService {

    private final PostRepository postRepository;


//    @Transactional //update라는 메서드가 SQL Query로서 작동하는 메소드라는 것을 알려줌
//    public Long update(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        post.update(requestDto);
//        // 변경이 잘 되었는지 확인
//        return post.getId();
//    }

    //Repository -> List<Post> -> List<PostReponseDto>
    //Repository에서 List<Post>로 받아온 데이터들을 List<PostResponseDto> 로 변환시켜준 다음, 리턴한다.
    public List<PostResponseDto> getAll(){
        //[1,2,3,4,5] --> ["1","2","3","4","5"]
        List<PostResponseDto> result  = new ArrayList<>();
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();

        for(Post post: postList) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            result.add(postResponseDto);
        }
        return result;
    }




    public PostDetailDto getOne(Long id){

        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isEmpty()){
            throw new IllegalArgumentException();
        } else {
//            Post post = optionalPost.get();
//            PostResponseDto postRequestDto = new PostResponseDto(post);
//            return postResponseDto;
            return new PostDetailDto(optionalPost.get());
        }
    }


}