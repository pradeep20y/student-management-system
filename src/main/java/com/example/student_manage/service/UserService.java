package com.example.student_manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.student_manage.dto.UserRequest;
import com.example.student_manage.dto.UserResponse;
import com.example.student_manage.model.Users;
import com.example.student_manage.repository.JpaRepo;

@Service
public class UserService {

    @Autowired
    public JpaRepo jpaRepo;

    public UserResponse signup(UserRequest UserRequest) {
        Users users = new Users(UserRequest.user, UserRequest.password, UserRequest.role);
        users = jpaRepo.save(users);
        return new UserResponse(users.getUser(), users.getRole());

    }
}
