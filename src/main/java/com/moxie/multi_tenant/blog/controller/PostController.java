package com.moxie.multi_tenant.blog.controller;

import com.moxie.multi_tenant.blog.common.ApiResponse;
import com.moxie.multi_tenant.blog.dto.PostCreateDto;
import com.moxie.multi_tenant.blog.dto.PostDto;
import com.moxie.multi_tenant.blog.service.PostService;
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
