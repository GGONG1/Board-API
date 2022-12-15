package com.team9.boardapi.dto;

import com.team9.boardapi.entity.Comment;
import com.team9.boardapi.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PostResponseDto {

    // 게시글 단순 조회, 게시글 작성에 사용될 Dto
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String contents;

    private String username;
    private Long likeCount = 0L;

    /*
        순환 참조를 막기 위해 List 제네릭 자료형을 Entity 대신 DTO로 변경
     */
    private List<CommentResponseDto> comments = new ArrayList<>();
    
    public PostResponseDto(Post post, Long likeCount) {
        this.id = post.getId();
        this.likeCount = likeCount;
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.title = post.getTitle();
        this.contents = post.getContent();
        this.username = post.getUser().getUsername();
        for (Comment comment:post.getCommentList() ) {
            this.comments.add(new CommentResponseDto(comment));
        }
    }
}
