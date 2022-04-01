package com.appliedscience.api.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    Optional<Project> findByName(String name);
}
