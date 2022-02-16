package com.appliedscience.api.web.controllers;

import com.appliedscience.api.services.ProjectService;
import com.appliedscience.api.shared.dto.ProjectDto;
import com.appliedscience.api.web.model.request.ProjectRequestEntity;
import com.appliedscience.api.web.model.response.ProjectResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProjectController {
    private final ProjectService service;
    private final ProjectMapper mapper;

    @Cacheable(cacheNames = {"projects"})
    @GetMapping
    @Operation(summary = "Gets all projects paginated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gets all projects paginated",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))}),
        @ApiResponse(responseCode = "404", description = "No projects found",
            content = @Content)
    })
    public Page<ProjectDto> getAll(@Parameter(description = "Maximum amount of items per page") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @Parameter(description = "Which page to get") @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        final var pageRequest = PageRequest.of(page, size);
        final var projects = service.getAll(pageRequest);
        return projects.map(mapper::toDto);

        List<ProjectDto> projectDtoList = service.get(page, size);
        List<ProjectResponseEntity> projectResponseEntityList;

        Type listType = new TypeToken<List<ProjectResponseEntity>>() {}.getType();
        projectResponseEntityList = mapper.map(projectDtoList, listType);

        return projectResponseEntityList;
    }

    @GetMapping("/{id}")
    public ProjectResponseEntity getAll(@PathVariable Long id) {
        ProjectDto projectDto = service.get(id);

        return mapper.map(projectDto, ProjectResponseEntity.class);
    }

    @PostMapping
    public ProjectResponseEntity create(@RequestBody ProjectRequestEntity projectRequestEntity) {
        return mapper.map(service.create(projectRequestEntity.getName(), projectRequestEntity.getDescription()), ProjectResponseEntity.class);
    }
}
