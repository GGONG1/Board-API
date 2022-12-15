package com.team9.boardapi.controller;

import com.team9.boardapi.dto.LikeRequestDto;
import com.team9.boardapi.dto.PostRequestDto;
import com.team9.boardapi.dto.PostResponseDto;
import com.team9.boardapi.dto.ResponseDto;
import com.team9.boardapi.entity.Post;
import com.team9.boardapi.security.UserDetailsImpl;
import com.team9.boardapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;



    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> read(@PathVariable Long id){
        return postService.getPost(id);
    }


    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDto>> readAll(){
        return postService.getPostList();
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){

        return postService.deletePost(id);
    }


    // 게시글 작성
    @PostMapping("")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return postService.createPost(requestDto, userDetails.getUser());
    }

    // 게시글 수정
    @PatchMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal
                                      UserDetailsImpl userDetails){

        return postService.updatePost(id, requestDto, userDetails.getUser());
    }

    //게시글 좋아요
    @PostMapping("/{id}/like")
    public ResponseDto<Post> postLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.insertLike(id, userDetails.getUser());
    }

}
