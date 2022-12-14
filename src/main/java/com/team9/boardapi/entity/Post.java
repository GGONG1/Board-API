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

public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userid;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "USER_ID", nullable = false)
//    private User user;

    public Post(PostRequestDto requestDto){
//        this.userid = requestDto.getUserid();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    // cascade는 왜 위험한가? 나중에 자세히 알아보자
    // 게시물이 삭제되면 게시물의 댓글도 함께 삭제되는 로직으로 만들어주세욥! ㅎㅇㅌ...
    // 어? 좋아요도 부탁드립니다.....
    // 지금은 A-Z repository DeleteBy~~로 합시다.
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();
}