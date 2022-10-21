package  com.sparta.week04_post1.repository;

//JpaRepository 를 PostRepository 에 상속
//게시글 조회

import  com.sparta.week04_post1.entity.Post;
import  com.sparta.week04_post1.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

                                        // 구현부X
                                        // 함수의 이름<파라미터, 반환형>
                                                   //<우리가 다룰 객체의 클래스, id의 자료>
public interface PostRepository extends JpaRepository<Post, Long> { //Long : 유일하게 데이터를 구분할 수 있는 자료의 형태

//어떠한 순서로 값을 가져올지: 수정된 순서를 내림차순으로 정렬해서 모든 값을 가져오겠다.
    List<Post> findAllByOrderByModifiedAtDesc();

 }