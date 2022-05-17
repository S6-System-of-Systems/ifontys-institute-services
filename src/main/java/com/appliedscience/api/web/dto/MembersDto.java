package com.appliedscience.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MembersDto {
    private String id;
    private String givenName;
    private String surname;
    private String displayName;
    private String mail;
    private String employeeId;
    private String photoUri;
}
