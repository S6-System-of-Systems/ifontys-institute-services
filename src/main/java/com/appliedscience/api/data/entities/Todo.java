package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Getter
@Setter
@Embeddable
public class Todo {
    private Long id;
    private String description;
}
