package  com.sparta.week04_post1.service;

//변경.수정이 잘되었는지 확인

import com.sparta.week04_post1.entity.Post;
import  com.sparta.week04_post1.repository.PostRepository;
import com.sparta.week04_post1.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // 생성자 주입: fanal 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어줌
@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;


    //게시글 전체 조회
    //Repository -> List<Post> -> List<PostReponseDto>
    //Repository에서 List<Post>로 받아온 데이터들을 List<PostResponseDto> 로 변환시켜준 다음, 리턴한다.
    public List<PostResponseDto> getAll() {
        //[1,2,3,4,5] --> ["1","2","3","4","5"]
        List<PostResponseDto> result = new ArrayList<>();
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();

        for (Post post : postList) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            result.add(postResponseDto);
        }
        return result;
    }


    //게시글 상세페이지 검색 - id 필요
    public PostDetailDto getOne(Long id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
//            Post post = optionalPost.get();
//            PostResponseDto postRequestDto = new PostResponseDto(post);
//            return postResponseDto;
            return new PostDetailDto(optionalPost.get());
        }
    }
//
//    @Transactional // update를 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다. 모든 작업들을 원상태로 되돌릴 수있다.
//    public Long update(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        post.update(requestDto);
//        // 변경이 잘 되었는지 확인
//        return post.getId();
//    }

//게시글 생성 - 게시글 데이터 필요
    @Transactional //데이터를 변경할 일이 생겼을 때 사용(update), // update 를 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다.
    public PostResponseDto create(PostRequestDto postRequestDto) {
        Post post = postRequestDto.toEntity();
        postRepository.save(post);
        return new PostResponseDto(post);
    }


//비밀번호 확인
//    1. 확인할 게시물을 가져온다.
//    2. 비교한다.
    public PasswordCheckResponseDto passwordCheck(Long id, PasswordCheckRequestDto passwordCheckRequestDto) {

        Optional<Post> optionalPost = postRepository.findById(id);

        //값이 없다면
        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            //내부에 값이 존재하므로 값을 꺼낸다.
            Post post = optionalPost.get();

            //비밀번호 값이 일치하는지 확인한다.
            boolean match = post.passwordMatch(passwordCheckRequestDto.getPassword());

            //원하는 리턴타입을 만든 후 리턴한다.
            return new PasswordCheckResponseDto(match);
        }
    }

//게시글 수정
//    1. 수정할 게시글을 가져온다.
//    2. 게시글을 수정한다.
//    3. db에 수정된 게시글을 지정한다.
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto postRequestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        //게시글이 없다면(true)
        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            Post post = optionalPost.get();

            post.update(
                    postRequestDto.getTitle(),
                    postRequestDto.getAuthor(),
                    postRequestDto.getContent(),
                    postRequestDto.getPassword());

            postRepository.save(post);

            return new PostResponseDto(post);
        }
    }


//게시글 삭제
    @Transactional
    public PostDeleteResponseDto delete(Long id){
            postRepository.deleteById(id);
            return new PostDeleteResponseDto(true);
    }

}