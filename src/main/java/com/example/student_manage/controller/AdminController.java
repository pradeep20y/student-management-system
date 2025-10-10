package com.example.student_manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.student_manage.dto.UserRequest;
import com.example.student_manage.dto.UserResponse;
import com.example.student_manage.model.Users;
import com.example.student_manage.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @GetMapping("/getUsers")
    public List<Users> getUsers() {
        return userService.getUser();
    }

    @GetMapping("/getUsers/id")
    public Users getUsers(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/createUsers")
    public UserResponse createUsers(@RequestBody UserRequest request) {
        return userService.createUsers(request);
    }

    @PostMapping("/updateUsers/{id}")
    public UserResponse updateUsers(@PathVariable Integer id, @RequestBody UserRequest request) {
        return userService.updateUsers(id, request);
    }

    @PostMapping("/deleteUsers/{id}")
    public String deleteUsers(@PathVariable Integer id) {
        return userService.deleteUsers(id);
    }

}
