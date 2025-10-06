package com.example.student_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student_manage.model.Users;
import java.util.Optional;

@Repository
public interface JpaRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUser(String username);
}
