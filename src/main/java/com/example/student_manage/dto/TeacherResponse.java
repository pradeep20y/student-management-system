package com.example.student_manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {
    private Integer id;

    private String name;
    private String email;
    private String department;
    private String subject;
    private String user;

}
