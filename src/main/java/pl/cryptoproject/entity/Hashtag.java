package pl.cryptoproject.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long userId;

    public Hashtag(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public Hashtag() {

    }
}
