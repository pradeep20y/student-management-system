package com.example.student_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student_manage.model.Teacher;
import com.example.student_manage.model.Users;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

    Teacher findTeacherByUser(Users user);

}