package be.technifutur.poupier.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter @Setter
public class OrderDetail {

    @Id
    @EmbeddedId
    private OrderDetailID id;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "quantity")
    private int qtt;

    private double discount;
}
