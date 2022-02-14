package com.appliedscience.api.web.controllers;

import com.appliedscience.api.services.ProjectService;
import com.appliedscience.api.shared.dto.ProjectDto;
import com.appliedscience.api.web.model.request.ProjectRequestEntity;
import com.appliedscience.api.web.model.response.ProjectResponseEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;
    private final ModelMapper mapper;

    public ProjectController(ProjectService service) {
        this.service = service;
        this.mapper = new ModelMapper();
    }

    @GetMapping
    public List<ProjectResponseEntity> get(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int limit) {
        List<ProjectDto> projectDtoList = service.get(page, limit);
        List<ProjectResponseEntity> projectResponseEntityList;

        Type listType = new TypeToken<List<ProjectResponseEntity>>() {}.getType();
        projectResponseEntityList = mapper.map(projectDtoList, listType);

        return projectResponseEntityList;
    }

    @GetMapping("/{id}")
    public ProjectResponseEntity get(@PathVariable Long id) {
        ProjectDto projectDto = service.get(id);

        return mapper.map(projectDto, ProjectResponseEntity.class);
    }

    @PostMapping
    public ProjectResponseEntity create(@RequestBody ProjectRequestEntity projectRequestEntity) {
        return mapper.map(service.create(projectRequestEntity.getName(), projectRequestEntity.getDescription()), ProjectResponseEntity.class);
    }
}
