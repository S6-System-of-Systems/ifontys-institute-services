package com.appliedscience.api.web.controllers;

import com.appliedscience.api.domain.exceptions.ProjectAlreadyExistsException;
import com.appliedscience.api.domain.exceptions.ProjectNotFoundException;
import com.appliedscience.api.domain.exceptions.TeacherAlreadyExistsException;
import com.appliedscience.api.domain.exceptions.TeacherNotFoundException;
import com.appliedscience.api.domain.services.TeacherService;
import com.appliedscience.api.web.dto.ProjectDto;
import com.appliedscience.api.web.dto.TeacherDto;
import com.appliedscience.api.web.mapper.TeacherMapper;
import com.appliedscience.api.web.model.request.ProjectRequestModel;
import com.appliedscience.api.web.model.request.TeacherRequestModel;
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
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Validated
@Slf4j
public class TeacherController {
    private final TeacherService service;
    private final TeacherMapper mapper;

    @Cacheable(cacheNames = {"teachers"})
    @GetMapping
    @Operation(summary = "Gets all teachers paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets all teachers paginated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "404", description = "No teachers found",
                    content = @Content)
    })
    public Page<TeacherDto> getAll(@Parameter(description = "Maximum amount of items per page") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @Parameter(description = "Which page to get") @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        final var pageRequest = PageRequest.of(page, size);
        final var teachers = service.findAll(pageRequest);
        return teachers.map(mapper::toDto);
    }

    @GetMapping("/{id}")
    @Cacheable(cacheNames = {"teachers"}, key = "#id")
    @Operation(summary = "Gets a teacher by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets a teacher by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherDto.class))}),
            @ApiResponse(responseCode = "404", description = "teachers not found",
                    content = @Content)
    })
    public TeacherDto getById(@Parameter(description = "Teacher id") @PathVariable(value = "id") Long id) throws TeacherNotFoundException {
//        return new TeacherDto(1L, "John Doe", LocalDateTime.now(), "something@something.com", "123456789");
        log.info("Get project with id {}", id);
        final var optionalTeacher = service.findById(id);
        var teacher = optionalTeacher.orElseThrow(() -> new TeacherNotFoundException(id));
        log.info("Mapping result : {}", teacher);
        return mapper.toDto(teacher);
    }

    @PostMapping
    @CacheEvict(cacheNames = {"teachers"}, key = "#name")
    @Operation(summary = "Creates a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherDto.class))}),
            @ApiResponse(responseCode = "400", description = "Teacher already exists",
                    content = @Content)
    })
    public TeacherDto create(@Valid @RequestBody final TeacherRequestModel teacherRequestModel) throws TeacherAlreadyExistsException {
        var teacher = service.findByName(teacherRequestModel.getName());
        if(teacher.isPresent()) {
            log.info("Teacher with name {} already exists", teacherRequestModel.getName());
            throw new TeacherAlreadyExistsException(teacherRequestModel.getName());
        } else {
            log.info("Creating project with name {}", teacherRequestModel.getName());
            teacher = Optional.of(mapper.toModel(teacherRequestModel));
        }
        log.info("Mapping result : {}", teacher.get());
        final var result = service.save(teacher.get());
        return mapper.toDto(result);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = {"teachers"}, key = "#id")
    @Operation(summary = "Deletes a teacher by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid argument",
                    content = @Content)
    })
    public ResponseEntity<?> remove(@Parameter(description = "Teacher id") @PathVariable Long id) {
        log.info("Removing teacher with id {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void clearCaches() {
        System.out.println("Evicting cache");
        evictCaches();
    }

    @CacheEvict(cacheNames = {"teachers"}, allEntries = true)
    public void evictCaches() {
    }

    @ExceptionHandler({ TeacherNotFoundException.class })
    public ResponseEntity<String> handleNotFoundExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler({ TeacherAlreadyExistsException.class })
    public ResponseEntity<String> handleAlreadyExistsExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
