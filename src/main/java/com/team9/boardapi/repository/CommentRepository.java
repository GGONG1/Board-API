package com.team9.boardapi.repository;

import com.team9.boardapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByPostId(Long postId);//게시글 삭제 될 때 해당 댓글 모두 삭제
}
