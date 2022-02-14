package com.appliedscience.api.services.impl;

import com.appliedscience.api.exceptions.ProjectNotFoundException;
import com.appliedscience.api.io.entities.ProjectEntity;
import com.appliedscience.api.io.repositories.ProjectRepository;
import com.appliedscience.api.services.ProjectService;
import com.appliedscience.api.shared.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<ProjectDto> get(int page, int limit) {
        List<ProjectDto> projects = new ArrayList<>();

        if (page > 0) page -= 1;

        Pageable pageable = PageRequest.of(page, limit);

        Page<ProjectEntity> projectPage = repository.findAll(pageable);
        List<ProjectEntity> project = projectPage.getContent();

        for(ProjectEntity projectEntity : project) {
            ProjectDto projectDto = mapper.map(projectEntity, ProjectDto.class);
            projects.add(projectDto);
        }

        return projects;
    }

    @Override
    public ProjectDto get(Long id) {
        Optional<ProjectEntity> project = repository.findById(id);

        if(project.isEmpty()) throw new ProjectNotFoundException(id.toString());

        return mapper.map(project.get(), ProjectDto.class);
    }

    @Override
    public ProjectDto create(String name, String description) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(name);
        projectEntity.setDescription(description);

        repository.save(projectEntity);

        return mapper.map(projectEntity, ProjectDto.class);
    }
}
