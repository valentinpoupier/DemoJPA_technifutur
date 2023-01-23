package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "quantity_per_unit")
    private String qttPerUnit;

    @Column(name = "units_in_stock")
    private int stock;

    @Column(name = "units_on_order")
    private  int inOrder;

    @Column(name = "reorder_level")
    private int reorderLevel;

    @Column(name="unit_price")
    private double price;

    private int discontinued;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
