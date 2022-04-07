package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity(name = "sharepoint")
public class Sharepoint extends BaseEntity{
    private String username;
    private String workPhone;
    @ElementCollection
    private List<String> currentProjects;
    @ElementCollection
    private List<String> ambitions;
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
    private String lastName;
    private String office;
    @ElementCollection
    private List<String> responsibilities;
    @ElementCollection
    private List<String> skills;
    private String title;
}
