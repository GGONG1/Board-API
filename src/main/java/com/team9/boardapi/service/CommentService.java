package com.team9.boardapi.service;

import com.team9.boardapi.dto.CommentRequestDto;
import com.team9.boardapi.dto.CommentResponseDto;
import com.team9.boardapi.entity.Comment;
import com.team9.boardapi.entity.User;
import com.team9.boardapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 달기
    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = new Comment(postId,commentRequestDto, user);
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment, user);
        return new ResponseEntity<>(commentResponseDto,HttpStatus.OK);
    }

    //댓글 수정
    @Transactional
    public ResponseEntity<CommentResponseDto> updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findByIdAndUserId(id,user.getId()).orElseThrow(
                () -> new IllegalArgumentException("선택한 댓글을 찾을 수 없습니다")
        );

        comment.update(commentRequestDto);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment, user);
        return new ResponseEntity<>(commentResponseDto,HttpStatus.OK);
    }
}
