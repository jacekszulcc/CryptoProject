package pl.cryptoproject.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Double quantity;

    private Long userId;

    private int cmcId;

    public Crypto(String name, Double quantity, Long userId, int cmcId) {
        this.name = name;
        this.quantity = quantity;
        this.userId = userId;
        this.cmcId = cmcId;
    }

    public Crypto() {
    }
}
