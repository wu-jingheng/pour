package com.moxie.pour.controller;

import com.moxie.pour.common.ApiResponse;
import com.moxie.pour.dto.UserCreateDto;
import com.moxie.pour.dto.UserDto;
import com.moxie.pour.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> create(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserDto userDto = userService.createUser(userCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(userDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAll() {
        List<UserDto> usersDto = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(usersDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(userDto));
    }
}
