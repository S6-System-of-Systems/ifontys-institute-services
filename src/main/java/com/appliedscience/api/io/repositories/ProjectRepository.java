package com.appliedscience.api.io.repositories;

import com.appliedscience.api.io.entities.ProjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<ProjectEntity, Long> {
}
