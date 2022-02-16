package com.appliedscience.api.io.repositories;

import com.appliedscience.api.io.entities.Project;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
}
