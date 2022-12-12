package com.team9.boardapi.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "Id")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}
