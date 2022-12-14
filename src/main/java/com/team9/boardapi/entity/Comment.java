package com.team9.boardapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    // cascade는 왜 위험한가? 나중에 자세히 알아보자
    // 댓글이 삭제되면 좋아요도 함께 삭제 부탁드립니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
