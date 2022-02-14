package com.appliedscience.api.services;

import com.appliedscience.api.shared.dto.TeacherDto;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    TeacherDto get(Long id);
    TeacherDto create(TeacherDto teacherDto);
}
