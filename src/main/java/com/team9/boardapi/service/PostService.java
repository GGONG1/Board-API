package com.team9.boardapi.service;

import com.team9.boardapi.dto.PostRequestDto;
import com.team9.boardapi.dto.PostResponseDto;
import com.team9.boardapi.dto.ResponseDto;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.entity.User;
import com.team9.boardapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);

        return new PostResponseDto(post);
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(

        );
        post.update(requestDto.getTitle(), requestDto.getContent());
        postRepository.save(post);
        return new PostResponseDto(post);
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
}
