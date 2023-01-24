package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "region")
@Getter @Setter
public class Region {
    @Id
    @Column(name = "region_id")
    private Short id;

    @Column(name = "region_description")
    private String regionDescription;

    @OneToMany(mappedBy = "region")
    private Set<Territory> territories = new LinkedHashSet<>();

}