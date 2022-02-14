package com.appliedscience.api.io.repositories;

import com.appliedscience.api.io.entities.TeacherEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<TeacherEntity, Long> {
}
