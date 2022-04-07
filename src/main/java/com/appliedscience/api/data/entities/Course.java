package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Getter
@Setter
@Embeddable
public class Course{
    private String id;
    private String projectNaam;
    private String taakNaam;
    private String opmerkingen;
}
