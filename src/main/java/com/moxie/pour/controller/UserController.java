package com.moxie.pour.controller;

import com.moxie.pour.common.ApiResponse;
import com.moxie.pour.dto.UserCreateDto;
import com.moxie.pour.dto.UserDto;
import com.moxie.pour.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a user")
    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> create(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserDto userDto = userService.createUser(userCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(userDto));
    }

    @Operation(summary = "Get all users (paginated)")
    @GetMapping("/paged")
    public ResponseEntity<ApiResponse<Page<UserDto>>> getAllPaged(Pageable pageable) {
        Page<UserDto> page = userService.getAllUsers(pageable);
        return ResponseEntity.ok(new ApiResponse<>(page));
    }

    @Operation(summary = "Get all user (non-paginated)")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAll() {
        List<UserDto> usersDto = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(usersDto));
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(userDto));
    }
}
