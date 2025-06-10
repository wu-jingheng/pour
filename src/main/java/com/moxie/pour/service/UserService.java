package com.moxie.pour.service;

import com.moxie.pour.dto.UserCreateDto;
import com.moxie.pour.dto.UserDto;
import com.moxie.pour.mapper.UserMapper;
import com.moxie.pour.model.Tenant;
import com.moxie.pour.model.User;
import com.moxie.pour.repository.TenantRepository;
import com.moxie.pour.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
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
