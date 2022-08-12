package pl.cryptoproject.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CryptoPortfolioDto {

    private Long id;
    private String name;

    private Double quantity;

    private Double price;

    private int cmcId;

    public CryptoPortfolioDto(Long id, String name, Double quantity, Double price, int cmcId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cmcId = cmcId;
    }
}
