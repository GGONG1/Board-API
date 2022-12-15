package com.team9.boardapi.mapper;

import com.team9.boardapi.dto.CommentRequestDto;
import com.team9.boardapi.dto.CommentResponseDto;
import com.team9.boardapi.entity.Comment;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.entity.User;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    //CommentRequestDto -> Comment
    public Comment toEntity(CommentRequestDto commentRequestDto, User user, Post post) {
        return Comment.builder()
                .content(commentRequestDto.getContent())
                .user(user)
                .post(post)
                .build();
    }

    //Comment -> CommentResponseDto
    public CommentResponseDto commentToCommentResponseDto(Comment comment, User user) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setId(comment.getId());
        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setLikeCount(comment.getLikeCount());
        commentResponseDto.setUsername(user.getUsername());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        commentResponseDto.setModifiedAt(comment.getModifiedAt());

        return commentResponseDto;
    }
}
