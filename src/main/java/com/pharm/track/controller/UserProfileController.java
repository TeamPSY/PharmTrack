package com.pharm.track.controller;

import com.pharm.track.dtos.UserDto;
import com.pharm.track.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserProfileController {

    private final UserService userService;

    /** 🔹 회원 상세 조회 */
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /** 🔹 회원 수정 */
    @PutMapping("/{id}")
    public String updateUser(
            @PathVariable Long id,
            @RequestBody UserDto dto
    ) {
        userService.updateUser(id, dto);
        return "OK";
    }
}