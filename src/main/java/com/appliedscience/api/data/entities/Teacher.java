package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Entity(name = "teachers")
@Getter
@Setter
public class Teacher extends BaseEntity {
    private String name;
    private LocalDateTime dob;
    private String email;
    private String phone;

    @Singular
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_projects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projectSet;

    public Set<Project> getProjectSet() {
        return Collections.unmodifiableSet(projectSet);
    }
}
