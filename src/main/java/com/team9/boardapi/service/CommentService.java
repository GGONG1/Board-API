package com.team9.boardapi.service;

import com.team9.boardapi.entity.Comment;
import com.team9.boardapi.entity.CommentLike;
import com.team9.boardapi.entity.User;
import com.team9.boardapi.entity.UserRoleEnum;
import com.team9.boardapi.repository.CommentLikeRepository;
import com.team9.boardapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    /*---- 댓글 삭제 ----*/
    @Transactional
    public ResponseEntity<String> deleteComment(Long commentId, User user) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        UserRoleEnum userRoleEnum = user.getRole();

        // 댓글을 작성한 유저가 아닐 때, 혹은 관리자가 아닐 때 Exception
        if (!comment.getUser().getId().equals(user.getId()) || userRoleEnum != UserRoleEnum.ADMIN) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        // 입력된 id의 게시물을 삭제
        commentRepository.deleteById(commentId);

        // 결과 반환
        return new ResponseEntity<String>("게시물 삭제 성공", HttpStatus.OK);
    }

    /*---- 댓글 좋아요 등록/취소 ----*/
    public ResponseEntity<String> like(long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        Optional<CommentLike> like = commentLikeRepository.findByUser_ID(user.getId());

        if (like.isPresent()) { // 이미 좋아요가 존재할 때
            CommentLike selectedLike = like.get();
            // 좋아요를 삭제
            commentLikeRepository.delete(selectedLike);
            return new ResponseEntity<String>("좋아요를 취소하였습니다.", HttpStatus.OK);
        } else { // 좋아요가 존재하지 않을 때
            CommentLike newLike = new CommentLike(user, comment);
            // 좋아요를 등록(저장)
            commentLikeRepository.save(newLike);
            return new ResponseEntity<String>("좋아요를 등록했습니다.", HttpStatus.OK);
        }
    }
}