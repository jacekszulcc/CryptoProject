package pl.cryptoproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.cryptoproject.entity.Crypto;
import pl.cryptoproject.entity.Hashtag;
import pl.cryptoproject.entity.User;

import java.util.List;


@Repository
public interface CryptoRepository  extends CrudRepository<Crypto, Long> {

    List<Crypto> findCryptoByUserId(Long id);

}
