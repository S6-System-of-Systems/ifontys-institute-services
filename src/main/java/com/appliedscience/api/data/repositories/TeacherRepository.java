package com.appliedscience.api.data.repositories;

import com.appliedscience.api.data.entities.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {
    Optional<Teacher> findByName(String name);
}
