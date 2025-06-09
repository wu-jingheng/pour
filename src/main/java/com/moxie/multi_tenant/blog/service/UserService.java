package com.moxie.multi_tenant.blog.service;

import com.moxie.multi_tenant.blog.dto.UserCreateDto;
import com.moxie.multi_tenant.blog.dto.UserDto;
import com.moxie.multi_tenant.blog.mapper.UserMapper;
import com.moxie.multi_tenant.blog.model.Tenant;
import com.moxie.multi_tenant.blog.model.User;
import com.moxie.multi_tenant.blog.repository.TenantRepository;
import com.moxie.multi_tenant.blog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final TenantRepository tenantRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       TenantRepository tenantRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tenantRepository = tenantRepository;
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        Tenant tenant = tenantRepository.findById(userCreateDto.getTenantId())
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));
        user.setTenant(tenant);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.toDto(user);
    }
}
