package be.technifutur.poupier.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "suppliers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @Column(name = "supplier_id")
    private Long id;

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

    private String homepage;

}
