package com.team9.boardapi.repository;

import com.team9.boardapi.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Long countByPost_Id(Long id);

    Long countByUser_IdAndPost_Id(Long id, Long id1);

    PostLike findPostLikeByPost_IdAndUserId(Long id, Long id1);

    void deleteByPostId(Long id);
}
