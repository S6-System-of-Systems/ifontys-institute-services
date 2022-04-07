package com.appliedscience.api.web.mapper;

import com.appliedscience.api.data.entities.Sharepoint;
import com.appliedscience.api.web.dto.SharepointDto;
import com.appliedscience.api.web.model.request.SharepointRequestModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SharepointMapper {
    SharepointDto toDto(Sharepoint sharepoint);

    @Mapping(target = "username", source = "username")
    Sharepoint toModel(SharepointRequestModel model);

    void updateModel(@MappingTarget Sharepoint sharepoint, SharepointRequestModel model);
}
