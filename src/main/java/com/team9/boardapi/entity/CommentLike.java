package com.team9.boardapi.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

// CommentLike Entity
@Entity
@NoArgsConstructor
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Comment_ID", nullable = false)
    private Comment comment;
}

