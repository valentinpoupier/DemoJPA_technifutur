package be.technifutur.poupier.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employee_id")
    private Short id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "title")
    private String title;

    @Column(name = "title_of_courtesy")
    private String titleOfCourtesy;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "extension")
    private String extension;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "reports_to")
    private Employee reportsTo;

    @OneToMany(mappedBy = "reportsTo")
    private Set<Employee> subordinates;

    @Column(name = "photo_path")
    private String photoPath;

    @ManyToMany
    @JoinTable(name = "employee_territories",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "territory_id"))
    private Set<Territory> territories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "managedBy")
    private Set<Order> orders = new LinkedHashSet<>();

}