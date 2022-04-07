package com.appliedscience.api.web.controllers;

import com.appliedscience.api.domain.exceptions.SharepointAlreadyExistsException;
import com.appliedscience.api.domain.exceptions.SharepointNotFoundException;
import com.appliedscience.api.domain.services.SharepointService;
import com.appliedscience.api.web.dto.SharepointDto;
import com.appliedscience.api.web.mapper.SharepointMapper;
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
@RequestMapping("/sharepoint")
@RequiredArgsConstructor
@Validated
@Slf4j
public class SharepointController {
    private final SharepointService service;
    private final SharepointMapper mapper;

    @Cacheable(cacheNames = {"sharepoint"})
    @GetMapping
    @Operation(summary = "Gets all sharepoint paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets all sharepoint paginated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "404", description = "No sharepoint found",
                    content = @Content)
    })
    public Page<SharepointDto> getAll(@Parameter(description = "Maximum amount of items per page") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @Parameter(description = "Which page to get") @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        final var pageRequest = PageRequest.of(page, size);
        final var sharepoint = service.findAll(pageRequest);
        return sharepoint.map(mapper::toDto);
    }

    @GetMapping("/{id}")
    @Cacheable(cacheNames = {"sharepoint"}, key = "#id")
    @Operation(summary = "Gets a sharepoint by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gets a sharepoint by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SharepointDto.class))}),
            @ApiResponse(responseCode = "404", description = "sharepoint not found",
                    content = @Content)
    })
    public SharepointDto getById(@Parameter(description = "Sharepoint id") @PathVariable(value = "id") Long id) throws SharepointNotFoundException {
//        return new SharepointDto(1L, "John Doe", LocalDateTime.now(), "something@something.com", "123456789");
        log.info("Get project with id {}", id);
        final var optionalSharepoint = service.findById(id);
        var sharepoint = optionalSharepoint.orElseThrow(() -> new SharepointNotFoundException(id));
        log.info("Mapping result : {}", sharepoint);
        return mapper.toDto(sharepoint);
    }

    @PostMapping
    @Operation(summary = "Creates a sharepoint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sharepoint created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SharepointDto.class))}),
            @ApiResponse(responseCode = "400", description = "Sharepoint already exists",
                    content = @Content)
    })
    public SharepointDto create(@Valid @RequestBody final SharepointRequestModel sharepointRequestModel) throws SharepointAlreadyExistsException {
        var sharepoint = service.findByUsername(sharepointRequestModel.getUsername());
        if(sharepoint.isPresent()) {
            log.info("Sharepoint with name {} already exists", sharepointRequestModel.getUsername());
            throw new SharepointAlreadyExistsException(sharepointRequestModel.getUsername());
        } else {
            log.info("Creating project with name {}", sharepointRequestModel.getUsername());
            sharepoint = Optional.of(mapper.toModel(sharepointRequestModel));
        }
        log.info("Mapping result : {}", sharepoint.get());
        final var result = service.save(sharepoint.get());
        return mapper.toDto(result);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = {"sharepoint"}, key = "#id")
    @Operation(summary = "Deletes a sharepoint by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sharepoint deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid argument",
                    content = @Content)
    })
    public ResponseEntity<?> remove(@Parameter(description = "Sharepoint id") @PathVariable Long id) {
        log.info("Removing sharepoint with id {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void clearCaches() {
        System.out.println("Evicting cache");
        evictCaches();
    }

    @CacheEvict(cacheNames = {"sharepoint"}, allEntries = true)
    public void evictCaches() {
    }

    @ExceptionHandler({ SharepointNotFoundException.class })
    public ResponseEntity<String> handleNotFoundExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler({ SharepointAlreadyExistsException.class })
    public ResponseEntity<String> handleAlreadyExistsExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
