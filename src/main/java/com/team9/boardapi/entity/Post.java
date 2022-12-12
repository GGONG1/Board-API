package com.team9.boardapi.entity;

import com.team9.boardapi.dto.PostRequestDto;
import lombok.AllArgsConstructor;
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

    @Column(nullable = false)
    private Long userid;

    @OneToMany(mappedBy = "Id")
    private List<Comment> commentList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    public Post(PostRequestDto requestDto){
        //this.userid = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
