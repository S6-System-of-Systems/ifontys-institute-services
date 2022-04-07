package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity(name = "canvas")
public class Canvas extends BaseEntity{

    @ElementCollection
    private List<Course> courses;
    @ElementCollection
    private List<Assignment> assignments;
}
