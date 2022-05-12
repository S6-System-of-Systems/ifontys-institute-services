package com.appliedscience.api.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity(name = "canvas")
public class Canvas extends BaseEntity{

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
    @OneToMany
    private List<Groups> groups = null;
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
