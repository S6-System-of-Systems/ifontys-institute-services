package com.appliedscience.api.domain.services;

import com.appliedscience.api.data.entities.Project;
import com.appliedscience.api.web.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Page<Project> findAll(Pageable pageable);
    Optional<Project> findById(Long id);
    Optional<Project> findByName(String name);
    Project save(Project projectToSave);
    void delete(Long id);
}
