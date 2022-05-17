package com.appliedscience.api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Members {
    @Id
    private String id;
    private String givenName;
    private String surname;
    private String displayName;
    private String mail;
    private String employeeId;
    private String photoUri;
}
