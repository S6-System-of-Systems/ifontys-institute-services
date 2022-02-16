package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "projects")
@Getter
@Setter
public class Project extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Teacher> teacherSet = new HashSet<>();

    public Set<Teacher> getTeacherSet() {
        return Collections.unmodifiableSet(teacherSet);
    }

}
