package com.appliedscience.api.web.mapper;

import com.appliedscience.api.data.entities.Project;
import com.appliedscience.api.web.dto.ProjectDto;
import com.appliedscience.api.web.model.request.ProjectRequestModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProjectMapper {
    public abstract ProjectDto toDto(Project project);

    @Mappings({
            @Mapping(target = "name", source = "name")
    })
    public abstract Project toModel(ProjectRequestModel model);

    public abstract void updateModel(@MappingTarget Project project, ProjectRequestModel model);
}
