package pl.cryptoproject.dto;


import lombok.Data;

@Data
public class CryptoDto {
    private int id;
    private String name;
    private String symbol;
    private Double circulatingSupply;
    private Double price;
    private Double volume24h;
    private Double percentChange1h;
    private Double percentChange24h;
    private Double percentChange7d;
    private Double marketCap;

}
