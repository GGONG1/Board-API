package com.team9.boardapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Post_ID", nullable = false)
    private Post post;

    public PostLike(Post post, User user) {
        this.user = user;
        this.post = post;
    }
}

