package com.team9.boardapi.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostRequestDto {
    private Long id;
    private String username;
    private String title;
    private String content;
}
