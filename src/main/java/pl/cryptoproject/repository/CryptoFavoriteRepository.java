package pl.cryptoproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.cryptoproject.entity.CryptoFavorite;

import java.util.List;

@Repository
public interface CryptoFavoriteRepository  extends CrudRepository<CryptoFavorite, Long> {

    List<CryptoFavorite> findAllCryptoFavoriteByUserId(Long id);

}
