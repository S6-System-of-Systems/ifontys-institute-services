package com.appliedscience.api.domain.services.impl;

import com.appliedscience.api.domain.services.ProjectService;
import com.appliedscience.api.data.entities.Project;
import com.appliedscience.api.data.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Override
    public Page<Project> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Project> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Project save(Project projectToSave) {
        return repository.save(projectToSave);
    }

    @Override
    public void delete(Long id) {
        final var projectToDelete = repository.findById(id);
        projectToDelete.ifPresent(repository::delete);
    }


}
