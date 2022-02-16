package com.appliedscience.api.web.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeacherRequestModel {
    private String name;
    private LocalDateTime dob;
    private String email;
    private String phone;
}
