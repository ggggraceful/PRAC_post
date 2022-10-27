package com.sparta.codecolosseumbackend.service;

import com.sparta.codecolosseumbackend.S3.S3Uploader;
import com.sparta.codecolosseumbackend.dto.request.ProblemRequestDto;
import com.sparta.codecolosseumbackend.dto.response.CommentResponseDto;
import com.sparta.codecolosseumbackend.dto.response.ProblemResponseDto;
import com.sparta.codecolosseumbackend.dto.response.ResponseDto;
import com.sparta.codecolosseumbackend.entity.Comment;
import com.sparta.codecolosseumbackend.entity.Likes;
import com.sparta.codecolosseumbackend.entity.Member;
import com.sparta.codecolosseumbackend.entity.Problem;
import com.sparta.codecolosseumbackend.repository.CommentRepository;
import com.sparta.codecolosseumbackend.repository.LikesRepository;
import com.sparta.codecolosseumbackend.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;

    // 글 작성
    public ResponseDto<String> createProblem(Member member, ProblemRequestDto requestDto, MultipartFile file) throws IOException {
        try {
            // 이미지 업로드 .upload(파일, 경로)
            String imgPath = s3Uploader.upload(file,"images");
            // requestDto의 imgUrl을 imgPath의 값으로 설정
            requestDto.setImgUrl(imgPath);
            Problem problem = new Problem(member, requestDto);
            problemRepository.save(problem);
            return ResponseDto.success("등록 완료!");
        } catch (NullPointerException e) {
            System.out.println("회원이 존재하지 않습니다.");
            return ResponseDto.fail(HttpStatus.FORBIDDEN, "등록 실패");
        }

    }

    // 글 목록 가져오기
    public ResponseDto<List<ProblemResponseDto.ProblemList>> findAllProblems() {
        List<Problem> problems = problemRepository.findAllByOrderByCreatedAtDesc();
        List<ProblemResponseDto.ProblemList> problemLists = new ArrayList<>();
        for (Problem problem : problems) {
            Long likeNum = (long) likesRepository.findAllByProblem(problem).size();
            problemLists.add(new ProblemResponseDto.ProblemList(problem, likeNum));
        }
        return ResponseDto.success(problemLists);
    }

    // 글 목록 가져오기 (좋아요 많은 순)
    public ResponseDto<List<ProblemResponseDto.ProblemList>> findAllProblemsByLikes() {
        List<Problem> problems = problemRepository.findAllByOrderByLikeNumDesc();
        List<ProblemResponseDto.ProblemList> problemLists = new ArrayList<>();
        for (Problem problem : problems) {
            Long likeNum = (long) likesRepository.findAllByProblem(problem).size();
            problemLists.add(new ProblemResponseDto.ProblemList(problem, likeNum));
        }
        return ResponseDto.success(problemLists);
    }

    // 글 하나 가져오기
    public ResponseDto<ProblemResponseDto.ProblemDetail> findOneProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다"));
        Long likeNum = (long) likesRepository.findAllByProblem(problem).size();
        // 글의 comment 모두 가져오기
        List<Comment> commentList = commentRepository.findAllByProblem(problem);
        List<CommentResponseDto> comments = new ArrayList<>();
        for (Comment comment : commentList) {
            comments.add(new CommentResponseDto(comment));
        }
        ProblemResponseDto.ProblemDetail problemDetail = new ProblemResponseDto.ProblemDetail(problem, likeNum, comments);
        return ResponseDto.success(problemDetail);
    }

    // 글 수정하기
    @Transactional
    public ResponseDto<String> updateProblem(Member member, Long problemId, ProblemRequestDto requestDto) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다"));

        // 글의 username과 로그인 유저의 username 비교, 불일치 시 수정 불가
        if (!member.getUsername().equals(problem.getMember().getUsername())) {
            return ResponseDto.fail(HttpStatus.FORBIDDEN, "작성자만 수정 가능합니다.");
        }

        problem.updateProblem(requestDto);
        return ResponseDto.success("수정 완료!");
    }

    // 글 삭제하기
    @Transactional
    public ResponseDto<String> deleteProblem(Member member, Long problemId) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다"));

        // 글의 username과 로그인 유저의 username 비교, 불일치 시 삭제 불가
        if (!member.getUsername().equals(problem.getMember().getUsername())) {
            return ResponseDto.fail(HttpStatus.FORBIDDEN, "작성자만 삭제 가능합니다.");
        }

        problemRepository.deleteById(problemId);

        // 해당 글의 좋아요도 삭제
        List<Likes> likes = likesRepository.findAllByProblem(problem);
        likesRepository.deleteAll(likes);
        // 해당 글의 댓글도 삭제
        List<Comment> comment = commentRepository.findAllByProblem(problem);
        commentRepository.deleteAll(comment);
        return ResponseDto.success("삭제 완료!");
    }

    // 유효성 검사 - 존재하는 문제인지 확인
    public Problem isPresentProblem(Long id) {
        Optional<Problem> optionalPost = problemRepository.findById(id);
        return optionalPost.orElse(null);
    }
}
