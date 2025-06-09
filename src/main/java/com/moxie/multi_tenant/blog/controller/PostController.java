package com.moxie.multi_tenant.blog.controller;

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

    @GetMapping
    public List<PostDto> getAll() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostCreateDto postCreateDto) {
        return ResponseEntity.ok(postService.createPost(postCreateDto));
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}
