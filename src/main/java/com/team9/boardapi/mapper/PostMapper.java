package com.team9.boardapi.mapper;

import com.team9.boardapi.dto.PostRequestDto;
import com.team9.boardapi.dto.PostResponseDto;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post PostDtoToPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post();
        post.setId(postRequestDto.getId());
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getTitle());
        post.setUser(user);
        return post;
    }

    // Member -> MemberResponseDto
    public PostResponseDto postToPosetResponseDto(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setId(post.getId());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setContents(post.getTitle());
        postResponseDto.setCreateAt(post.getCreatedAt());
        postResponseDto.setModifiedAt(post.getModifiedAt());

        return postResponseDto;
    }
}
