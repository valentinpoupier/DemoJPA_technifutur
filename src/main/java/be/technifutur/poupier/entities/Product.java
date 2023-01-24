package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({@NamedQuery(name = "GET_ALL_PRODUCT", query = "SELECT p FROM Product p")})
public class Product {

    @Id
    @Column(name = "product_id")
    private long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "quantity_per_unit")
    private String qttPerUnit;

    @Column(name = "units_in_stock")
    private Integer stock;

    @Column(name = "units_on_order")
    private Integer onOrder;

    @Column(name = "reorder_level")
    private Integer reorderLevel;

    private Integer discontinued;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
