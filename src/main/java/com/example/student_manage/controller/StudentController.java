package com.example.student_manage.controller;

import com.example.student_manage.dto.StudentRequest;
import com.example.student_manage.dto.StudentResponse;
import com.example.student_manage.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('STUDENT')")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @PostMapping("/setprofile")
    public StudentResponse setProfile(@RequestBody StudentRequest studentRequest) {
        return userService.setStudentProfile(studentRequest);
    }

    @PostMapping("/updateprofile")
    public StudentResponse updateProfile(@RequestBody StudentRequest studentRequest) {
        return userService.updateStudentProfile(studentRequest);
    }

}
