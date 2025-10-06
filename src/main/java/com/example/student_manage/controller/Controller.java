package com.example.student_manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.student_manage.dto.UserRequest;
import com.example.student_manage.dto.UserResponse;
import com.example.student_manage.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Controller {

    @Autowired
    public UserService userService;

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserRequest userRequest) {
        return userService.signup(userRequest);
    }

}
