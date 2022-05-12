package com.appliedscience.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SharepointDto {
    private String username;
    private String workPhone;
    private String cellPhone;
    private String facebook;
    private String linkedIn;
    private List<String> schools;
    private String twitter;
    private List<String> pastEmployers;
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
    private List<String> interests;
    private String lastName;
    private String office;
    private List<String> responsibilities;
    private List<String> skills;
    private String title;
}
