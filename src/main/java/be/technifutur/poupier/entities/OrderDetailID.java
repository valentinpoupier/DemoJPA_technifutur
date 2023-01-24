package be.technifutur.poupier.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailID implements Serializable {

    private static final long serialVersionID = 1L;

    private long orderId;
    private long productId;

}
