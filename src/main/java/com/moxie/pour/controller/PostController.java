package com.moxie.pour.controller;

import com.moxie.pour.common.ApiResponse;
import com.moxie.pour.dto.PostCreateDto;
import com.moxie.pour.dto.PostDto;
import com.moxie.pour.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post", description = "Post management APIs")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Create a post")
    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> create(@Valid @RequestBody PostCreateDto postCreateDto) {
        PostDto postDto = postService.createPost(postCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(postDto));
    }

    @Operation(summary = "Get all posts (paginated)")
    @GetMapping("/paged")
    public ResponseEntity<ApiResponse<Page<PostDto>>> getAllPaged(Pageable pageable) {
        Page<PostDto> page = postService.getAllPosts(pageable);
        return ResponseEntity.ok(new ApiResponse<>(page));
    }

    @Operation(summary = "Get all posts (non-paginated)")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAll() {
        List<PostDto> postsDto = postService.getAllPosts();
        return ResponseEntity.ok(new ApiResponse<>(postsDto));
    }

    @Operation(summary = "Get post by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(new ApiResponse<>(postDto));
    }
}
