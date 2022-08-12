package pl.cryptoproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
public class CryptoFavoriteLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    private Double price;

    private double changePrice;

    @ManyToOne
    @JoinColumn(name = "cryptoId")
    private CryptoFavorite cryptoFavorite;

    public CryptoFavoriteLog() {   }

    public CryptoFavoriteLog(LocalDateTime data, Double price, double changePrice, CryptoFavorite cryptoFavorite) {
        this.data = data;
        this.price = price;
        this.changePrice = changePrice;
        this.cryptoFavorite = cryptoFavorite;
    }
}
