package com.appliedscience.api.web.model.response;

import lombok.Data;

import java.util.List;

@Data
public class SharepointResponseModel {
    private Long id;
    private String username;
    private List<String> currentProjects;
    private List<String> ambitions;
    private String goals;
    private String aboutMe;
    private List<String> responsibilities;
    private List<String> skills;
}
