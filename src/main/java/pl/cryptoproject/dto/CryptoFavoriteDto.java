package pl.cryptoproject.dto;

import lombok.Data;
import pl.cryptoproject.entity.CryptoFavoriteLog;

import java.util.ArrayList;
import java.util.List;

@Data
public class CryptoFavoriteDto {

    private Long id;

    private String name;

    private int cmcId;

    private List<CryptoFavoriteLog> cryptoFavoriteLogList;

    public CryptoFavoriteDto(Long id, String name, int cmcId, List<CryptoFavoriteLog> cryptoFavoriteLogList) {
        this.id = id;
        this.name = name;
        this.cmcId = cmcId;
        this.cryptoFavoriteLogList = cryptoFavoriteLogList;
    }
}
