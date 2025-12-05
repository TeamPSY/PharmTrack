package com.pharm.track.controller;

import com.pharm.track.dtos.UserDto;
import com.pharm.track.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto user) {
        userService.register(user);
        return "OK";
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto loginData, HttpSession session) {
        UserDto user = userService.login(loginData.getUsername(), loginData.getPassword());

        if (user == null) return null;

        session.setAttribute("user", user.getUserId());
        return user;
    }

    @GetMapping("/me")
    public Long me(HttpSession session) {
        return (Long) session.getAttribute("user");
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
