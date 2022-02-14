package com.appliedscience.api.shared.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherDto {
    private Long id;
    private String name;
    private Date dob;
    private String email;
    private String phone;
}
