package com.appliedscience.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SharepointDto {
    private Long id;
    private String username;
    private List<String> currentProjects;
    private List<String> ambitions;
    private String goals;
    private String aboutMe;
    private List<String> responsibilities;
    private List<String> skills;
}
