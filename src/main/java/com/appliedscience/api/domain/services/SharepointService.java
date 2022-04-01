package com.appliedscience.api.domain.services;

import com.appliedscience.api.data.entities.Sharepoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SharepointService {
    Page<Sharepoint> findAll(Pageable pageable);
    Optional<Sharepoint> findById(Long id);
    Optional<Sharepoint> findByUsername(String name);
    Sharepoint save(Sharepoint projectToSave);
    void delete(Long id);
}
