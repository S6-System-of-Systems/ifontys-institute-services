package com.appliedscience.api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Groups {
    @Id
    private String id;
    private String url;
    private String groupName;
    private String groupType;
    private String role;
    @OneToMany
    private List<Members> members = null;
    private String updatedAt;
    private Long numberOfMembers;
}
