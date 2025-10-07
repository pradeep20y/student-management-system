package com.example.student_manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.student_manage.dto.UserRequest;
import com.example.student_manage.dto.UserResponse;
import com.example.student_manage.model.Users;
import com.example.student_manage.repository.JpaRepo;

@Service
public class UserService {

    @Autowired
    public JpaRepo jpaRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public UserResponse signup(UserRequest UserRequest) {
        Users users = new Users(UserRequest.user, passwordEncoder.encode(UserRequest.getPassword()), UserRequest.role);
        users = jpaRepo.save(users);
        return new UserResponse(users.getUser(), users.getRole());

    }

    public List<Users> getUser() {
        List<Users> users = jpaRepo.findAll();
        return users;
    }

    public UserResponse createUsers(UserRequest UserRequest) {
        Users users = new Users(UserRequest.user, passwordEncoder.encode(UserRequest.getPassword()), UserRequest.role);
        users = jpaRepo.save(users);
        return new UserResponse(users.getUser(), users.getRole());
    }

    public UserResponse updateUsers(Integer id, UserRequest UserRequest) {
        Users existingUser = jpaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        // Update fields
        existingUser.setUser(UserRequest.getUser());
        existingUser.setRole(UserRequest.getRole());

        if (UserRequest.getPassword() != null && !UserRequest.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(UserRequest.getPassword()));
        }
        existingUser = jpaRepo.save(existingUser);
        return new UserResponse(existingUser.getUser(), existingUser.getRole());
    }

    public String deleteUsers(Integer id) {
        if (jpaRepo.existsById(id))
            jpaRepo.deleteById(id);
        return "Success";
    }

    public Users getUser(Integer id) {
        Optional<Users> user = jpaRepo.findById(id);
        if (user.isEmpty())
            return null;
        return user.get();

    }

}
