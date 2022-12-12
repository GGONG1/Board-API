package com.team9.boardapi.controller;

import com.team9.boardapi.dto.ResponseDto;
import com.team9.boardapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostContorller {

    private final PostService postService;


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> read(@PathVariable Long id){
        return postService.getPost(id);
    }



}
