package com.team9.boardapi.mapper;

import com.team9.boardapi.dto.PostRequestDto;
import com.team9.boardapi.dto.PostResponseDto;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toEntity(PostRequestDto requestDto, User user){

        return Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .user(user)
                .build();
    }


    public PostResponseDto postToPostResponseDto(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setId(post.getId());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setContents(post.getTitle());
        postResponseDto.setCreateAt(post.getCreatedAt());
        postResponseDto.setModifiedAt(post.getModifiedAt());

        return postResponseDto;
    }
}
