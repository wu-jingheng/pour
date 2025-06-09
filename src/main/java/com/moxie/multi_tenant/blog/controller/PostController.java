package com.moxie.multi_tenant.blog.controller;

import com.moxie.multi_tenant.blog.dto.PostDto;
import com.moxie.multi_tenant.blog.mapper.PostMapper;
import com.moxie.multi_tenant.blog.model.Post;
import com.moxie.multi_tenant.blog.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public PostController(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @GetMapping
    public List<PostDto> getAll() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @PostMapping
    public PostDto create(@RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return postMapper.toDto(post);
    }
}
