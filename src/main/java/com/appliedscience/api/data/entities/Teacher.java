package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "teachers")
@Getter
@Setter
public class Teacher extends BaseEntity {
    private String name;
    private LocalDateTime dob;
    private String email;
    private String phone;
}
