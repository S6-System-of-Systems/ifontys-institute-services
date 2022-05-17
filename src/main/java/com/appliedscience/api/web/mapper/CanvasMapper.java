package com.appliedscience.api.web.mapper;

import com.appliedscience.api.data.entities.Canvas;
import com.appliedscience.api.web.dto.CanvasDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CanvasMapper {
    @Mapping(target = "id", source = "id")
    CanvasDto toDto(Canvas canvas);

    @Mapping(target = "id", source = "id")
    Canvas toModel(CanvasDto model);

    void updateModel(@MappingTarget Canvas canvas, CanvasDto model);
}
