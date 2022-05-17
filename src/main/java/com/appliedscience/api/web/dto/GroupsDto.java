package com.appliedscience.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GroupsDto {
    private String id;
    private String url;
    private String groupName;
    private String groupType;
    private String role;
    private List<MembersDto> members = null;
    private String updatedAt;
    private Long numberOfMembers;
}
