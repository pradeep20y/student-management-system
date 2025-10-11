package com.example.student_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student_manage.model.Student;
import com.example.student_manage.model.Users;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    Student findTeacherByUser(Users user);

}
