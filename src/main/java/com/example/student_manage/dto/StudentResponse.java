package com.example.student_manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponse {

    private Integer id;
    private String name;
    private String email;
    private String department;
    private String course;
    private String user;

}
