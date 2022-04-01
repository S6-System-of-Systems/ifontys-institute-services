package com.appliedscience.api.domain.services.impl;

import com.appliedscience.api.data.entities.Sharepoint;
import com.appliedscience.api.data.repositories.SharepointRepository;
import com.appliedscience.api.domain.services.SharepointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SharepointServiceImpl implements SharepointService {
    private final SharepointRepository repository;

    @Override
    public Page<Sharepoint> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Optional<Sharepoint> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Sharepoint> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Sharepoint save(Sharepoint sharepointToSave) {
        return repository.save(sharepointToSave);
    }

    @Override
    public void delete(Long id) {
        final var sharepointToDelete = repository.findById(id);
        sharepointToDelete.ifPresent(repository::delete);
    }
}
