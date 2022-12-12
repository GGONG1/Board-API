package com.team9.boardapi.entity;

import com.team9.boardapi.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long userid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    public Post(PostRequestDto requestDto){
        this.userid = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
