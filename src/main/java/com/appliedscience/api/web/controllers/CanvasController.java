package com.appliedscience.api.web.controllers;

import com.appliedscience.api.domain.exceptions.CanvasAlreadyExistsException;
import com.appliedscience.api.domain.exceptions.CanvasNotFoundException;
import com.appliedscience.api.domain.exceptions.SharepointAlreadyExistsException;
import com.appliedscience.api.domain.exceptions.SharepointNotFoundException;
import com.appliedscience.api.domain.services.CanvasService;
import com.appliedscience.api.web.dto.CanvasDto;
import com.appliedscience.api.web.dto.SharepointDto;
import com.appliedscience.api.web.mapper.CanvasMapper;
import com.appliedscience.api.web.model.request.SharepointRequestModel;
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
@RequestMapping("/canvas")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CanvasController {

    private final CanvasService service;
    private final CanvasMapper mapper;

    @Cacheable(cacheNames = {"canvas"})
    @GetMapping
    @Operation(summary = "Gets all canvas paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets all canvas paginated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "404", description = "No canvas found",
                    content = @Content)
    })
    public Page<CanvasDto> getAll(@Parameter(description = "Maximum amount of items per page") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @Parameter(description = "Which page to get") @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        log.info("Getting all canvas");
        final var pageRequest = PageRequest.of(page, size);
        log.info("Getting all canvas with page request {}", pageRequest);
        final var canvas = service.findAll(pageRequest);
        log.info("Found {} canvas", canvas.getTotalElements());
        return canvas.map(mapper::toDto);
    }

    @Cacheable(cacheNames = {"canvas"}, key = "#id")
    @GetMapping("/{id}")
    @Operation(summary = "Gets a canvas by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets a canvas by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CanvasDto.class))}),
            @ApiResponse(responseCode = "404", description = "Canvas not found",
                    content = @Content)
    })
    public CanvasDto getById(@Parameter(description = "Canvas id") @PathVariable(value = "id") String id) throws CanvasNotFoundException {
        log.info("Getting canvas with id {}", id);
        final var optionalCanvas = service.findById(id);
        log.info("Found canvas {}", optionalCanvas);
        var canvas = optionalCanvas.orElseThrow(() -> new CanvasNotFoundException(id));
        log.info("Mapping canvas to dto and returning result");
        return mapper.toDto(canvas);
    }

    @PostMapping
    @Operation(summary = "Creates a canvas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Canvas created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CanvasDto.class))}),
            @ApiResponse(responseCode = "400", description = "Canvas already exists",
                    content = @Content)
    })
    public CanvasDto create(@Valid @RequestBody final CanvasDto model) throws CanvasAlreadyExistsException {
        log.info("Creating canvas with model {}", model);
        var canvas = service.findById(model.getId());
        if(canvas.isPresent()) {
            log.info("Given Canvas id already exists");
            throw new CanvasAlreadyExistsException(model.getId());
        } else {
            log.info("Creating canvas with model {}", model);
            canvas = Optional.of(mapper.toModel(model));
        }
        log.info("Saving canvas with model {}", canvas);
        final var result = service.save(canvas.get());
        return mapper.toDto(result);
    }
    @CacheEvict(cacheNames = {"canvas"}, key = "#id")
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a canvas by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Canvas deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid argument",
                    content = @Content)
    })
    public ResponseEntity<?> remove(@Parameter(description = "Canvas id") @PathVariable String id) {
        log.info("Removing canvas with id {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void clearCaches() {
        System.out.println("Evicting cache");
        evictCaches();
    }

    @CacheEvict(cacheNames = {"canvas"}, allEntries = true)
    public void evictCaches() {
    }

    @ExceptionHandler({ CanvasNotFoundException.class })
    public ResponseEntity<String> handleNotFoundExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler({ CanvasAlreadyExistsException.class })
    public ResponseEntity<String> handleAlreadyExistsExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


}
