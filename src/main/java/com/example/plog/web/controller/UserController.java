package com.example.plog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plog.config.security.CurrentUser;
import com.example.plog.security.UserPrincipal;
import com.example.plog.service.UserService;
import com.example.plog.web.dto.ApiResponse;
import com.example.plog.web.dto.user.UserResponseDto;
import com.example.plog.web.dto.user.UserUpdateDto;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/mypage")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserInfo(@CurrentUser UserPrincipal userPrincipal) {
        UserResponseDto response = userService.getUserInfo(userPrincipal);
        return ApiResponse.success(response);
    }
    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserUpdateDto updateInfo) {
        userService.updateUser(userPrincipal, updateInfo);
        return ApiResponse.success();
    }

    @GetMapping("/leavePet/{petName}")
    public ResponseEntity<ApiResponse<Void>> leavePet(@CurrentUser UserPrincipal userPrincipal, @PathVariable String petName) {
        userService.leavePet(userPrincipal,petName);
        return ApiResponse.success();
    }
}
