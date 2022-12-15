package com.team9.boardapi.repository;

import com.team9.boardapi.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUser_Id(Long user_id);

    void deleteAllByComment_Id(Long comment_id);
}
