package com.example.student_manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student_manage.dto.StudentRequest;
import com.example.student_manage.dto.StudentResponse;
import com.example.student_manage.dto.TeacherRequest;
import com.example.student_manage.dto.TeacherResponse;
import com.example.student_manage.dto.UserRequest;
import com.example.student_manage.dto.UserResponse;
import com.example.student_manage.model.Student;
import com.example.student_manage.model.Teacher;
import com.example.student_manage.model.Users;
import com.example.student_manage.repository.JpaRepo;
import com.example.student_manage.repository.StudentRepo;
import com.example.student_manage.repository.TeacherRepo;

@Service
public class UserService {

    @Autowired
    public JpaRepo jpaRepo;

    @Autowired
    public TeacherRepo teacherRepo;

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public UserResponse signup(UserRequest UserRequest) {

        Users users = new Users(UserRequest.user, passwordEncoder.encode(UserRequest.getPassword()),
                UserRequest.getRole());
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

    public TeacherResponse setProfile(TeacherRequest teacherRequest) {
        // Get currently logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = jpaRepo.findByUser(username).get();

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Create Teacher entity
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.getName());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setDepartment(teacherRequest.getDepartment());
        teacher.setSubject(teacherRequest.getSubject());
        teacher.setUser(user);

        // Save teacher directly
        teacher = teacherRepo.save(teacher); // Implement saveTeacher in JpaRepo

        // Prepare response
        return new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getDepartment(),
                teacher.getSubject(),
                user.getUser());
    }

    public TeacherResponse updateprofile(TeacherRequest teacherRequest) {
        // Get logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = jpaRepo.findByUser(username).get();
        if (user == null)
            throw new RuntimeException("User not found");

        // Find teacher profile linked to this user
        Teacher teacher = teacherRepo.findTeacherByUser(user);
        if (teacher == null)
            throw new RuntimeException("Teacher profile not found");

        // Update fields if provided
        if (teacherRequest.getName() != null)
            teacher.setName(teacherRequest.getName());
        if (teacherRequest.getEmail() != null)
            teacher.setEmail(teacherRequest.getEmail());
        if (teacherRequest.getDepartment() != null)
            teacher.setDepartment(teacherRequest.getDepartment());
        if (teacherRequest.getSubject() != null)
            teacher.setSubject(teacherRequest.getSubject());

        teacher = teacherRepo.save(teacher);

        return new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getDepartment(),
                teacher.getSubject(),
                user.getUser());
    }

    public List<Student> viewstudents() {
        return studentRepo.findAll(); // Implement in JpaRepo
    }

    public StudentResponse setStudentProfile(StudentRequest studentRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = jpaRepo.findByUser(username).get();

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Create Teacher entity
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setDepartment(studentRequest.getDepartment());
        student.setCourse(studentRequest.getCourse());
        student.setUser(user);

        // Save teacher directly
        student = studentRepo.save(student); // Implement saveTeacher in JpaRepo

        // Prepare response
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getCourse(),
                user.getUser());
    }

    public StudentResponse updateStudentProfile(StudentRequest studentRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = jpaRepo.findByUser(username).get();
        if (user == null)
            throw new RuntimeException("User not found");

        // Find teacher profile linked to this user
        Student student = studentRepo.findTeacherByUser(user);
        if (student == null)
            throw new RuntimeException("Student profile not found");

        // Update fields if provided
        if (studentRequest.getName() != null)
            student.setName(studentRequest.getName());
        if (studentRequest.getEmail() != null)
            student.setEmail(studentRequest.getEmail());
        if (studentRequest.getDepartment() != null)
            student.setDepartment(studentRequest.getDepartment());
        if (studentRequest.getCourse() != null)
            student.setCourse(studentRequest.getCourse());

        student = studentRepo.save(student);

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getCourse(),
                user.getUser());
    }

}
