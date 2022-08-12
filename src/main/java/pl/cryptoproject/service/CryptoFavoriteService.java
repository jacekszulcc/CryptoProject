package pl.cryptoproject.service;

import org.springframework.stereotype.Service;
import pl.cryptoproject.entity.CryptoFavorite;
import pl.cryptoproject.entity.CryptoFavoriteLog;
import pl.cryptoproject.repository.CryptoFavoriteLogRepository;
import pl.cryptoproject.repository.CryptoFavoriteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CryptoFavoriteService {

    private final CryptoFavoriteRepository cryptoFavoriteRepository;
    private final CryptoFavoriteLogRepository cryptoFavoriteLogRepository;

    public CryptoFavoriteService(CryptoFavoriteRepository cryptoFavoriteRepository, CryptoFavoriteLogRepository cryptoFavoriteLogRepository) {
        this.cryptoFavoriteRepository = cryptoFavoriteRepository;
        this.cryptoFavoriteLogRepository = cryptoFavoriteLogRepository;
    }


    public void saveCryptoFavorite(CryptoFavorite cryptoFavorite){
        cryptoFavoriteRepository.save(cryptoFavorite);
    }

    public void saveCryptoFavoriteLog(CryptoFavoriteLog cryptoFavoriteLog){
        cryptoFavoriteLogRepository.save(cryptoFavoriteLog);
    }

    public List<CryptoFavorite> findAllCryptoFavoriteByUserId(Long id){
        return cryptoFavoriteRepository.findAllCryptoFavoriteByUserId(id);
    }

    public List<CryptoFavoriteLog> findFirst3CryptoFavoriteLogsByCryptoId(CryptoFavorite cryptoFavorite){
        return cryptoFavoriteLogRepository.findFirst3CryptoFavoriteLogByCryptoFavoriteOrderByIdDesc(cryptoFavorite);
    }

    public void deleteByCryptoFavorite(CryptoFavorite cryptoFavorite){
          cryptoFavoriteRepository.delete(cryptoFavorite);
    }

    public CryptoFavorite findCryptoFavoriteById(Long id){
        return cryptoFavoriteRepository.findById(id).get();
    }

    public CryptoFavoriteLog findFirstCryptoFavoriteLogsByCryptoId(CryptoFavorite cryptoFavorite){
        return  cryptoFavoriteLogRepository.findFirstCryptoFavoriteLogByCryptoFavoriteOrderByIdDesc(cryptoFavorite);
    }

    public CryptoFavoriteLog findLastCryptoFavoriteLogsByCryptoId(CryptoFavorite cryptoFavorite){
        return  cryptoFavoriteLogRepository.findFirstCryptoFavoriteLogByCryptoFavoriteOrderByIdAsc(cryptoFavorite);
    }
}
