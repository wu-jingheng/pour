package com.moxie.multi_tenant.blog.service;

import com.moxie.multi_tenant.blog.dto.PostCreateDto;
import com.moxie.multi_tenant.blog.dto.PostDto;
import com.moxie.multi_tenant.blog.mapper.PostMapper;
import com.moxie.multi_tenant.blog.model.Post;
import com.moxie.multi_tenant.blog.model.Tenant;
import com.moxie.multi_tenant.blog.model.User;
import com.moxie.multi_tenant.blog.repository.PostRepository;
import com.moxie.multi_tenant.blog.repository.TenantRepository;
import com.moxie.multi_tenant.blog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, PostMapper postMapper,
                       TenantRepository tenantRepository,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        Post post = postMapper.toEntity(postCreateDto);
        Tenant tenant = tenantRepository.findById(postCreateDto.getTenantId())
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));
        User author = userRepository.findById(postCreateDto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        post.setTenant(tenant);
        post.setAuthor(author);

        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return postMapper.toDto(post);
    }
}
