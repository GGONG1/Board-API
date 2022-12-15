package com.team9.boardapi.controller;

import com.team9.boardapi.entity.User;
import com.team9.boardapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id, userDetails.getUser());
    }

    // 댓글 좋아요 / 좋아요 취소
    @PostMapping("/like/{id}")
    public ResponseEntity<String> likeComment(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.like(id, userDetails.getUser());
    }
}
