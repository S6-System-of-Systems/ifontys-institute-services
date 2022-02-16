package com.appliedscience.api.web.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeacherResponseModel {
    private Long id;
    private String name;
    private LocalDateTime dob;
    private String email;
    private String phone;
}
