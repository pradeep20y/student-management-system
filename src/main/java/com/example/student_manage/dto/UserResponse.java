package com.example.student_manage.dto;

import com.example.student_manage.model.Roles;

public class UserResponse {
    public String user;
    public Roles role;

    public UserResponse(String user, Roles role) {
        this.user = user;
        this.role = role;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public Roles getRole() {
        return role;
    }
}
