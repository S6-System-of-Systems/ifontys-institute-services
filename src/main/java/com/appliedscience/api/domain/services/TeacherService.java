package com.appliedscience.api.domain.services;

import com.appliedscience.api.data.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TeacherService {
    Page<Teacher> findAll(Pageable pageable);
    Optional<Teacher> findById(Long id);
    Optional<Teacher> findByName(String name);
    Teacher save(Teacher projectToSave);
    void delete(Long id);
}
