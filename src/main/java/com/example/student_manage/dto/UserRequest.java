package com.example.student_manage.dto;

import com.example.student_manage.model.Roles;

public class UserRequest {
    public String user;
    public String password;
    public Roles role;

    public UserRequest(String user, String password, Roles role) {
        this.user = user;
        this.password = password;
        this.role = role;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }

}
