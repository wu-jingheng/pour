package com.moxie.pour.controller;

import com.moxie.pour.common.ApiResponse;
import com.moxie.pour.dto.PostCreateDto;
import com.moxie.pour.dto.PostDto;
import com.moxie.pour.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> create(@Valid @RequestBody PostCreateDto postCreateDto) {
        PostDto postDto = postService.createPost(postCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(postDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAll() {
        List<PostDto> postsDto = postService.getAllPosts();
        return ResponseEntity.ok(new ApiResponse<>(postsDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(new ApiResponse<>(postDto));
    }
}
