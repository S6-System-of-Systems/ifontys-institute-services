package com.appliedscience.api.data.repositories;

import com.appliedscience.api.data.entities.Canvas;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CanvasRepository extends PagingAndSortingRepository<Canvas, Long> {
}
