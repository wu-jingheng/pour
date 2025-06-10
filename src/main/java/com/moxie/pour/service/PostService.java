package com.moxie.pour.service;

import com.moxie.pour.dto.PostCreateDto;
import com.moxie.pour.dto.PostDto;
import com.moxie.pour.mapper.PostMapper;
import com.moxie.pour.model.Post;
import com.moxie.pour.model.Tenant;
import com.moxie.pour.model.User;
import com.moxie.pour.repository.PostRepository;
import com.moxie.pour.repository.TenantRepository;
import com.moxie.pour.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PostDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postMapper::toDto);
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
