package com.appliedscience.api.services;

import com.appliedscience.api.shared.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> get(int page, int limit);
    ProjectDto get(Long id);
    ProjectDto create(String name, String description);
}
