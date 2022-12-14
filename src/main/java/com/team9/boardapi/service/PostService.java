package com.team9.boardapi.service;

import com.team9.boardapi.dto.PostRequestDto;
import com.team9.boardapi.dto.PostResponseDto;
import com.team9.boardapi.dto.ResponseDto;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.entity.PostLike;
import com.team9.boardapi.entity.User;
import com.team9.boardapi.repository.PostLikeRepository;
import com.team9.boardapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final PostLikeRepository postLikeRepository;

    // 게시글 작성
    @Transactional
    public void createPost(Post post) {
        postRepository.save(post);
    }


    // 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if(post.getUser().getId() != user.getId()){throw new IllegalArgumentException("권한이 없습니다.");}

        post.update(requestDto.getTitle(), requestDto.getContent());

        postRepository.save(post);
        Long count = postLikeRepository.countByPost_Id(id);
        return new PostResponseDto(post, count);
    }

    public ResponseEntity<String> deletePost(Long id) {

        postRepository.delete(
                postRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("해당 글을 찾을 수 없습니다.")
                )
        );
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<String>("삭제성공", httpStatus);
    }

    @Transactional
    public ResponseEntity<PostResponseDto> getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("에러발생")
        );//post 글 하나 읽어와서 조회
        PostResponseDto postResponseDto = new PostResponseDto(post);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<PostResponseDto>(postResponseDto, httpStatus);
    }

    @Transactional()
    public ResponseEntity<List<PostResponseDto>> getPostList() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDto> result = new ArrayList<>();
        for(Post post : postList){
            result.add(new PostResponseDto(post));
        }
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<List<PostResponseDto>>(result, httpStatus);
    }

    // 좋아요 추가/삭제
    @Transactional
    public ResponseDto<Post> insertLike(Long id, User user) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        Long count = postLikeRepository.countByUser_IdAndPost_Id(user.getId(),id);

        if (count == 0L){ // 좋아요가 존재하지 않을 때
            PostLike postLike = new PostLike(post, user);
            postLikeRepository.save(postLike);
            return new ResponseDto<Post>("좋아요 등록", 200, post);
        }else { // 이미 좋아요를 했을 때
            PostLike postLike = postLikeRepository.findPostLikeByPost_IdAndUserId(id, user.getId());
            postLikeRepository.deleteById(postLike.getId());
            return new ResponseDto<Post>("좋아요 삭제", 200, post);
        }


    }


}
