package com.appliedscience.api.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@AllArgsConstructor
public class CanvasDto {
    private String id;
    private String url;
    private String givenName;
    private String surName;
    private String initials;
    private String displayName;
    private String mail;
    private String office;
    private String telephoneNumber;
    private String mobileNumber;
    private String photo;
    private String department;
    private String personalTitel;
    private List<GroupsDto> groups = null;
    private Long lat;
    @JsonProperty("long")
    private Long _long;
    @ElementCollection
    private List<String> affiliations;
    private String updatedAt;
    private String uid;
    private String thumbnailData;
    private String employeeId;
}
