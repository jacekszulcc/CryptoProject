package pl.cryptoproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.cryptoproject.entity.CryptoFavorite;
import pl.cryptoproject.entity.CryptoFavoriteLog;

import java.util.List;

@Repository
public interface CryptoFavoriteLogRepository  extends CrudRepository<CryptoFavoriteLog, Long> {

    List<CryptoFavoriteLog> findFirst3CryptoFavoriteLogByCryptoFavoriteOrderByIdDesc(CryptoFavorite cryptoFavorite);

    CryptoFavoriteLog findFirstCryptoFavoriteLogByCryptoFavoriteOrderByIdDesc(CryptoFavorite cryptoFavorite);

    CryptoFavoriteLog findFirstCryptoFavoriteLogByCryptoFavoriteOrderByIdAsc(CryptoFavorite cryptoFavorite);

}
