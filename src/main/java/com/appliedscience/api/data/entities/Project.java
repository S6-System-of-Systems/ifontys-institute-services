package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "projects")
@Getter
@Setter
public class Project extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private String description;
}
