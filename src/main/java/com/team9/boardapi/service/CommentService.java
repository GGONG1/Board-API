package com.team9.boardapi.service;

import com.team9.boardapi.dto.CommentRequestDto;
import com.team9.boardapi.dto.CommentResponseDto;
import com.team9.boardapi.entity.Comment;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.entity.User;
import com.team9.boardapi.entity.UserRoleEnum;
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
    private final PostRepository postRepository;

    //댓글 달기
    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        Comment comment = new Comment(post,commentRequestDto, user);
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment, user);
        return new ResponseEntity<>(commentResponseDto,HttpStatus.OK);
    }

    //댓글 수정
    @Transactional
    public ResponseEntity<CommentResponseDto> updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 댓글을 찾을 수 없습니다")
        );

        //사용자 권한에 따른 댓글 수정
        UserRoleEnum userRoleEnum = user.getRole();

        //댓글을 작성한 사용자와 동일한 사용자가 아닐 때, 권한이 관리자가 아닐 때
        if(userRoleEnum != UserRoleEnum.ADMIN && !comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("수정을 할 수 없습니다.");
        }

        comment.update(commentRequestDto);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment, user);
        return new ResponseEntity<>(commentResponseDto,HttpStatus.OK);
    }
}
