package com.appliedscience.api.web.model.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SharepointRequestModel {
    private Long id;
    private String username;
    private List<String> currentProjects;
    private List<String> ambitions;
    private String goals;
    private String aboutMe;
    private List<String> responsibilities;
    private List<String> skills;
}
