package com.team9.boardapi.repository;


import com.team9.boardapi.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Long countByPost_Id(Long id);
    Optional<PostLike> findPostLikeByPost_IdAndUserId(Long id,Long id2);
}
