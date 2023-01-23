package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_id")
    private String id;

    @Column(name = "company_Name")
    private String companyName;

    @Column(name = "contact_Name")
    private String contactName;

    @Column(name = "contact_title")
    private String contactTitle;

    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    private String city;

    private String region;

    private String country;

    private String phone;

    private String fax;

    @ManyToMany
    @JoinTable(
            name = "customer_customer_demo",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_type_id")
    )
    private List<Demographic> types;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
