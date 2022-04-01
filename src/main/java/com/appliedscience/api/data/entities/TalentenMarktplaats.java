package com.appliedscience.api.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity(name = "talenten_marktplaats")
public class TalentenMarktplaats extends BaseEntity {
        private String overMij;
        private String vraag;
        private String kennis;
        @ElementCollection
        private List<String> projecten;
        private String opleiding;
        private String talenten;
        @ElementCollection
        private List<String> inzet;
        private String functie;
        private String rol;
        private String linkedIn;
}
