package com.appliedscience.api.web.mapper;

import com.appliedscience.api.web.dto.TeacherDto;
import com.appliedscience.api.web.model.request.TeacherRequestModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TeacherMapper {
    public abstract TeacherDto toDto(Teacher Teacher);

    @Mappings({
            @Mapping(target = "name", source = "name")
    })
    public abstract Teacher toModel(TeacherRequestModel model);

    public abstract void updateModel(@MappingTarget Teacher Teacher, TeacherRequestModel model);
}
