package com.team9.boardapi.service;

import com.team9.boardapi.dto.PostRequestDto;
import com.team9.boardapi.dto.PostResponseDto;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);

        return new PostResponseDto(post);
    }
}
