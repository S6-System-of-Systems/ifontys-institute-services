package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "sharepoint")
public class Sharepoint extends BaseEntity{
    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String workPhone;
    @Column(nullable = true)
    private String cellPhone;
    @Column(nullable = true)
    private String facebook;
    @Column(nullable = true)
    private String linkedIn;
    @Column(nullable = true)
    @ElementCollection
    private List<String> schools;
    @Column(nullable = true)
    private String twitter;
    @Column(nullable = true)
    @ElementCollection
    private List<String> pastEmployers;
    @ElementCollection
    private List<String> currentProjects;
    @ElementCollection
    private List<String> ambitions;
    @Column(columnDefinition = "text")
    private String contributions;
    @Column(columnDefinition = "text")
    private String goals;
    private String schedule;
    private String photoUri;
    private String videoLink;
    private String projectDescriptions;
    private String telLink;
    @Column(columnDefinition = "text")
    private String aboutMe;
    private String department;
    private String displayName;
    private String emailAdress;
    private String firstName;
    @Column(nullable = true)
    @ElementCollection
    private List<String> interests;
    private String lastName;
    private String office;
    @ElementCollection
    private List<String> responsibilities;
    @ElementCollection
    private List<String> skills;
    private String title;
}
