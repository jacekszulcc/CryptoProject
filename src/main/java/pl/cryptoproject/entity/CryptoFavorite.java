package pl.cryptoproject.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CryptoFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int cmcId;

    private Long userId;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cryptoId")
    private List<CryptoFavoriteLog> cryptoFavoriteLogList = new ArrayList<>();

    public CryptoFavorite() {
    }

    public CryptoFavorite(String name, int cmcId, Long userId) {
        this.name = name;
        this.cmcId = cmcId;
        this.userId = userId;
    }

    public void addLog(CryptoFavoriteLog cryptoFavoriteLog) {
        cryptoFavoriteLogList.add(cryptoFavoriteLog);
    }

}
