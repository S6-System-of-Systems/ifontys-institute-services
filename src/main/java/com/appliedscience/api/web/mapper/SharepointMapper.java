package com.appliedscience.api.web.mapper;

import com.appliedscience.api.data.entities.Sharepoint;
import com.appliedscience.api.web.dto.SharepointDto;
import com.appliedscience.api.web.model.request.SharepointRequestModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SharepointMapper {
    public abstract SharepointDto toDto(Sharepoint sharepoint);

    @Mappings({
            @Mapping(target = "username", source = "username")
    })
    public abstract Sharepoint toModel(SharepointRequestModel model);

    public abstract void updateModel(@MappingTarget Sharepoint sharepoint, SharepointRequestModel model);
}
