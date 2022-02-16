package com.appliedscience.api.domain.services.impl;

import com.appliedscience.api.data.entities.Teacher;
import com.appliedscience.api.data.repositories.TeacherRepository;
import com.appliedscience.api.domain.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;

    @Override
    public Page<Teacher> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Teacher> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Teacher save(Teacher teacherToSave) {
        return repository.save(teacherToSave);
    }

    @Override
    public void delete(Long id) {
        final var teacherToDelete = repository.findById(id);
        teacherToDelete.ifPresent(repository::delete);
    }
}
