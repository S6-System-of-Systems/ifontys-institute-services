package com.appliedscience.api.io.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "teachers")
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date dob;
    private String email;
    private String phone;
}
