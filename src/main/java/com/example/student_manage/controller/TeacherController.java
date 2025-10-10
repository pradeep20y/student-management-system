package com.example.student_manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_manage.dto.TeacherRequest;
import com.example.student_manage.dto.TeacherResponse;
import com.example.student_manage.model.Student;
import com.example.student_manage.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasRole('TEACHER')")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    public UserService userService;

    @GetMapping("/tea")
    public String pingtea() {
        return "teaping";
    }

    @PostMapping("/setprofile")
    public TeacherResponse setprofile(@RequestBody TeacherRequest teacherRequest) {
        return userService.setProfile(teacherRequest);
    }

    @PostMapping("/updateprofile")
    public TeacherResponse updateprofile(@RequestBody TeacherRequest teacherRequest) {
        return userService.updateprofile(teacherRequest);
    }

    @GetMapping("/viewstudents")
    public List<Student> viewstudents() {
        return userService.viewstudents();
    }

}
