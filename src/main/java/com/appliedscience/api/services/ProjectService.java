package com.appliedscience.api.services;

import com.appliedscience.api.io.entities.Project;
import com.appliedscience.api.shared.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    Page<Project> findAll(Pageable page);
    List<ProjectDto> get(int page, int limit);
    ProjectDto get(Long id);
    ProjectDto create(String name, String description);
}
