package com.appliedscience.api.data.repositories;

import com.appliedscience.api.data.entities.Sharepoint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SharepointRepository extends PagingAndSortingRepository<Sharepoint, Long> {
    Optional<Sharepoint> findByUsername(String name);
}
