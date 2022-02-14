package com.appliedscience.api.io.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "projects")
@Getter
@Setter
public class ProjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private String description;
}
