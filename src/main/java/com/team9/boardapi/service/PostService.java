package com.team9.boardapi.service;

import com.team9.boardapi.dto.PostRequestDto;
import com.team9.boardapi.dto.PostResponseDto;
import com.team9.boardapi.dto.ResponseDto;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public ResponseEntity<ResponseDto> getPost(Long id) {
        Optional<Post> post = postRepository.findById(id);//post 글 하나 읽어와서 조회
        postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("에러발생")
        );
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseDto<Post> responseDto = new ResponseDto<>("", httpStatus.value(), post.get());
        return new ResponseEntity<ResponseDto>(responseDto, httpStatus);
    }
}
