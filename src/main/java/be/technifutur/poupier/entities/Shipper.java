package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "shippers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipper {

    @Id
    @Column(name = "shipper_id")
    private long id;

    @Column(name = "company_name")
    private String companyName;

    private String phone;

    @OneToMany(mappedBy = "shipper")
    private List<Order> orders;

}
