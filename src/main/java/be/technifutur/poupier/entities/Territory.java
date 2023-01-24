package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "territories")
@Getter @Setter
public class Territory {
    @Id
    @Column(name = "territory_id")
    private String id;

    @Column(name = "territory_description")
    private String territoryDescription;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToMany(mappedBy = "territories")
    private Set<Employee> employees = new LinkedHashSet<>();

}