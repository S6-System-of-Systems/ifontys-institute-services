package com.appliedscience.api.web.model.response;

import lombok.Data;

import java.util.List;

@Data
public class SharepointResponseModel {
    private String username;
    private String workPhone;
    private List<String> currentProjects;
    private List<String> ambitions;
    private String contributions;
    private String goals;
    private String schedule;
    private String photoUri;
    private String videoLink;
    private String projectDescriptions;
    private String telLink;
    private String aboutMe;
    private String department;
    private String displayName;
    private String emailAdress;
    private String firstName;
    private String lastName;
    private String office;
    private List<String> responsibilities;
    private List<String> skills;
    private String title;
}
