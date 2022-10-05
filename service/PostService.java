package com.sparta.week03.service;

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

@RequiredArgsConstructor 
@Service
public class PostService {

    private final PostRepository postRepository;


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
            return new PostDetailDto(optionalPost.get());
        }
    }


}
