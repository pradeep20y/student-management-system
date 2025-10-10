package com.example.student_manage.dto;

import com.example.student_manage.model.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    public String user;
    public String password;
    public Roles role;

}
