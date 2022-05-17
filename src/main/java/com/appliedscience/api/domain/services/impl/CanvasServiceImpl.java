package com.appliedscience.api.domain.services.impl;

import com.appliedscience.api.data.entities.Canvas;
import com.appliedscience.api.data.entities.Sharepoint;
import com.appliedscience.api.data.repositories.CanvasRepository;
import com.appliedscience.api.domain.services.CanvasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CanvasServiceImpl implements CanvasService {

    private final CanvasRepository repository;

    @Override
    public Page<Canvas> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Optional<Canvas> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Canvas save(Canvas projectToSave) {
        return repository.save(projectToSave);
    }

    @Override
    public void delete(String id) {
        final var projectToDelete = repository.findById(id);
        projectToDelete.ifPresent(repository::delete);
    }
}
