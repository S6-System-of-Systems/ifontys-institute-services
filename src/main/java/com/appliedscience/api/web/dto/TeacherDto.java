package com.appliedscience.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class TeacherDto {
    private Long id;
    private String name;
    private LocalDateTime dob;
    private String email;
    private String phone;


}
