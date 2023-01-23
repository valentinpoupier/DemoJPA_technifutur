package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @Column(name = "order_id")
    private long id;

    @Column(name = "customer_id")
    private String  customerId;

    @Column(name = "employee_id")
    private long employeeId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "required_date")
    private LocalDate required;

    @Column(name = "shipped_date")
    private LocalDate shipped;

    private float freight;

    @Column(name = "ship_name")
    private String name;

    @Column(name = "ship_address")
    private String address;

    @Column(name = "ship_city")
    private String city;

    @Column(name = "ship_region")
    private String region;

    @Column(name = "ship_postal_code")
    private String postalCode;

    @Column(name = "ship_country")
    private String country;

    @ManyToMany
    @JoinTable(
            name = "order_details",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "ship_via")
    private Shipper shipper;

}
