package com.team9.boardapi.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostRequestDto {
    private Long userId = 1L;
    private String title;
    private String content;
}
