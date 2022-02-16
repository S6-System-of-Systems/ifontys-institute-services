package com.appliedscience.api.web.controllers;

import com.appliedscience.api.domain.exceptions.ProjectAlreadyExistsException;
import com.appliedscience.api.domain.exceptions.ProjectNotFoundException;
import com.appliedscience.api.domain.services.ProjectService;
import com.appliedscience.api.web.dto.ProjectDto;
import com.appliedscience.api.web.mapper.ProjectMapper;
import com.appliedscience.api.web.model.request.ProjectRequestModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
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
        final var projects = service.findAll(pageRequest);
        return projects.map(mapper::toDto);
    }

    @GetMapping("/{id}")
    @Cacheable(cacheNames = {"projects"}, key = "#id")
    @Operation(summary = "Gets a project by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gets a project by id",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProjectDto.class))}),
        @ApiResponse(responseCode = "404", description = "Project not found",
            content = @Content)
    })
    public ProjectDto getById(@Parameter(description = "Project id") @PathVariable(value = "id") Long id) throws ProjectNotFoundException {
        log.info("Get project with id {}", id);
        final var optionalProject = service.findById(id);
        var project = optionalProject.orElseThrow(() -> new ProjectNotFoundException(id));
        log.info("Mapping result : {}", project);
        return mapper.toDto(project);
    }

    @PostMapping
    @CacheEvict(cacheNames = {"projects"}, key = "#name")
    @Operation(summary = "Creates a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProjectDto.class))}),
            @ApiResponse(responseCode = "400", description = "Project already exists",
                    content = @Content)
    })
    public ProjectDto create(@Valid @RequestBody final ProjectRequestModel projectRequestModel) throws ProjectAlreadyExistsException {
        var project = service.findByName(projectRequestModel.getName());
        if(project.isPresent()) {
            log.info("Project with name {} already exists", projectRequestModel.getName());
            throw new ProjectAlreadyExistsException(projectRequestModel.getName());
        } else {
            log.info("Creating project with name {}", projectRequestModel.getName());
            project = Optional.of(mapper.toModel(projectRequestModel));
        }
        log.info("Mapping result : {}", project.get());
        final var result = service.save(project.get());
        return mapper.toDto(result);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = {"projects"}, key = "#id")
    @Operation(summary = "Deletes a project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid argument",
                    content = @Content)
    })
    public ResponseEntity<?> remove(@Parameter(description = "Project id") @PathVariable Long id) {
        log.info("Removing project with id {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void clearCaches() {
        System.out.println("Evicting cache");
        evictCaches();
    }

    @CacheEvict(cacheNames = {"projects"}, allEntries = true)
    public void evictCaches() {
    }

    @ExceptionHandler({ ProjectNotFoundException.class })
    public ResponseEntity<String> handleNotFoundExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler({ ProjectAlreadyExistsException.class })
    public ResponseEntity<String> handleAlreadyExistsExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
