package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity(name = "sharepoint")
public class Sharepoint extends BaseEntity{
    private String username;
    @ElementCollection
    private List<String> currentProjects;
    @ElementCollection
    private List<String> ambitions;
    private String goals;
    private String aboutMe;
    @ElementCollection
    private List<String> responsibilities;
    @ElementCollection
    private List<String> skills;
}
