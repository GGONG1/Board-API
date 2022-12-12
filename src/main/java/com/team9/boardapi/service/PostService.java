package com.team9.boardapi.service;


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
    PostRepository postRepository;
    /*게시글 하나 조회하기*/
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
